package com.moi.lime.core.dispatch

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface Dispatchers {
    fun provideMain(): CoroutineContext
    fun provideIO(): CoroutineContext
    fun provideUnconfined():CoroutineContext
    fun provideDefault():CoroutineContext
}