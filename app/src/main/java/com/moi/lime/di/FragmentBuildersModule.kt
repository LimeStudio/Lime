package com.moi.lime.di

import com.moi.lime.ui.SecondFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeSecondFragment(): SecondFragment

}