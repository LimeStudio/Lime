package com.moi.lime.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

//object LiveDataTestUtil {
//    fun <T> getValue(liveData: LiveData<T>): T {
//        val data = arrayOfNulls<Any>(1)
//        val latch = CountDownLatch(1)
//        val observer = object : Observer<T> {
//            override fun onChanged(o: T?) {
//                data[0] = o
//                latch.countDown()
//                liveData.removeObserver(this)
//            }
//        }
//        liveData.observeForever(observer)
//        latch.await(2, TimeUnit.SECONDS)
//
//        @Suppress("UNCHECKED_CAST")
//        return data[0] as T
//    }
//}

inline fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
    val observer = Observer<T> { Unit }
    try {
        observeForever(observer)
        block()
    } finally {
        removeObserver(observer)
    }
}