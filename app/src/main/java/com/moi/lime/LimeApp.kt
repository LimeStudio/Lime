package com.moi.lime

import android.app.Activity
import android.app.Application
import com.moi.lime.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class LimeApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    companion object {
        lateinit var instance: LimeApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}