package com.moi.lime.util

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.moi.lime.vo.Resource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@SuppressLint("CheckResult")
fun <T> Flowable<T>.asLiveData(): LiveData<Resource<T>> {
    val liveData = MutableLiveData<Resource<T>>()
    liveData.value = Resource.loading(null)
    subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val resource = Resource.success(it)
                liveData.value = resource
            }, {
                val resource = Resource.error(it, null)
                liveData.value = resource
            })
    return liveData
}

@SuppressLint("CheckResult")
fun <T> Single<T>.asLiveData(): LiveData<Resource<T>> {
    return toFlowable().asLiveData()
}


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