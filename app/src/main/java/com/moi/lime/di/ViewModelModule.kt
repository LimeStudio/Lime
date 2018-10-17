package com.moi.lime.di

import androidx.lifecycle.ViewModelProvider
import com.moi.lime.viewmodel.LimeViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule{

//    @Binds
//    @IntoMap
//    @ViewModelKey(RepoViewModel::class)
//    abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: LimeViewModelFactory): ViewModelProvider.Factory
}