package com.moi.lime.util

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.moi.lime.vo.Resource
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

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

fun <X, Y> LiveData<X>.switchMap(block: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this) { block(it) }
}

fun <X, Y> LiveData<X>.map(block: (X) -> Y): LiveData<Y> {
    return Transformations.map(this) { block(it) }
}

fun <T> ViewModel.resourceLiveData(
        context: CoroutineContext = viewModelScope.coroutineContext + Dispatchers.IO,
        timeoutInMs: Long = 5000L,
        block: suspend () -> T): LiveData<Resource<T>> {
    return liveData(context, timeoutInMs) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(block()))
        } catch (e: Exception) {
            emit(Resource.error(e, null))
        }
    }

}