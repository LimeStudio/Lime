package com.moi.lime.util

import com.moi.lime.core.dispatch.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher

class TestDispatchers(private val coroutineDispatcher: CoroutineDispatcher) : Dispatchers {
    override fun provideMain() = coroutineDispatcher
    override fun provideIO() = coroutineDispatcher
    override fun provideUnconfined() = coroutineDispatcher
    override fun provideDefault() = coroutineDispatcher

}