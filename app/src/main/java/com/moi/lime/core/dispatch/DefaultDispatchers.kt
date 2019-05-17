package com.moi.lime.core.dispatch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DefaultDispatchers : com.moi.lime.core.dispatch.Dispatchers {
    @ExperimentalCoroutinesApi
    override fun provideUnconfined() = Dispatchers.Unconfined

    override fun provideDefault() = Dispatchers.Default

    override fun provideMain() = Dispatchers.Main

    override fun provideIO() = Dispatchers.IO

}