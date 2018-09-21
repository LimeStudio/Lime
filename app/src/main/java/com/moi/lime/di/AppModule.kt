package com.moi.lime.di

import android.app.Application
import androidx.room.Room
import com.moi.lime.api.MoiService
import com.moi.lime.db.LimeDb
import com.moi.lime.db.ProfileDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideMoiService(): MoiService {
        return Retrofit.Builder()
                .baseUrl("http://www.moi.pub:3000/")
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
}