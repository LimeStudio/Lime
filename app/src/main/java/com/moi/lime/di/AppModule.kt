package com.moi.lime.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.moi.lime.LimeApp
import com.moi.lime.api.MoiService
import com.moi.lime.api.SignInExpireInterceptor
import com.moi.lime.core.user.Cardinal
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDb
import com.moi.lime.db.ProfileDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideMoiService(context: Context): MoiService {
        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))

        val okHttpClient = OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addInterceptor(SignInExpireInterceptor())
                .build()

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://www.lime.kim:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MoiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): LimeDb {
        return Room
                .databaseBuilder(app, LimeDb::class.java, "lime.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideProfile(db: LimeDb): ProfileDao {
        return db.profileDao()
    }

    @Singleton
    @Provides
    fun provideUserManager(profileDao: ProfileDao): UserManager {
        return Cardinal(profileDao)
    }

    @Singleton
    @Provides
    fun provideContext(): Context {
        return LimeApp.instance
    }
}