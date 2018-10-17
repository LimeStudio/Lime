package com.moi.lime.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moi.lime.vo.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.reset

@RunWith(JUnit4::class)
class LiveDataExtKtTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxRule = RxSchedulerRule()

    @Test
    fun testAsLiveData() {
        asyncToSync()
        val observer = mock<Observer<Resource<Int>>>()
        var emitter : FlowableEmitter<Int>? = null
        val flowable = Flowable.create(FlowableOnSubscribe<Int> {
           emitter=it
        }, BackpressureStrategy.BUFFER)

        flowable.asLiveData().observeForever(observer)
        Mockito.verify(observer).onChanged(Resource.loading(null))

        reset(observer)
        emitter?.onNext(1)
        Mockito.verify(observer).onChanged(Resource.success(1))

        reset(observer)
        val throwable = Throwable()
        emitter?.onError(throwable)
        Mockito.verify(observer).onChanged(Resource.error(throwable,null))

    }
}