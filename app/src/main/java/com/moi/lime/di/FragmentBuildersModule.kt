package com.moi.lime.di

import com.moi.lime.ui.home.HomeFragment
import com.moi.lime.ui.home.profile.ProfileFragment
import com.moi.lime.ui.home.recommend.RecommendFragment
import com.moi.lime.ui.home.recommend.RecommendItemFragment
import com.moi.lime.ui.play.PlayPageFragment
import com.moi.lime.ui.signin.SignInFragment
import com.moi.lime.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment

    @ContributesAndroidInjector
    abstract fun contributeRecommendFragment(): RecommendFragment

    @ContributesAndroidInjector
    abstract fun contributeRecommendItemFragment(): RecommendItemFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePlayPageFragment(): PlayPageFragment

}