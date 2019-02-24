package com.moi.lime.di

import com.moi.lime.ui.home.HomeFragment
import com.moi.lime.ui.SecondFragment
import com.moi.lime.ui.signin.SignInFragment
import com.moi.lime.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeSecondFragment(): SecondFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment

}