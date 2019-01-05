package com.moi.lime.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moi.lime.ui.splash.SplashViewModel
import com.moi.lime.viewmodel.LimeViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: SplashViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: LimeViewModelFactory): ViewModelProvider.Factory
}