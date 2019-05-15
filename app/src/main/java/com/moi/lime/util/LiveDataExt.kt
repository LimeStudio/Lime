package com.moi.lime.util

import androidx.lifecycle.liveData
import com.moi.lime.vo.Resource
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T> resourceLiveData(
        context: CoroutineContext = EmptyCoroutineContext,
        timeoutInMs: Long = 5000L,
        block: suspend () -> T
) = liveData(context, timeoutInMs) {
    try {
        emit(Resource.loading(null))
        emit(Resource.success(block()))
    } catch (e: Exception) {
        emit(Resource.error(e, null))
    }
}