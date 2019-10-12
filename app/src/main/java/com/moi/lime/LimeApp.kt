package com.moi.lime

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import com.moi.lime.core.user.UserManager
import com.moi.lime.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LimeApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var userManager: UserManager

    companion object {
        lateinit var instance: LimeApp
    }

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
        instance = this
        AppInjector.init(this)
        GlobalScope.launch {
            userManager.init()
        }

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}