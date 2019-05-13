package com.moi.lime.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moi.lime.vo.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.reset
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class LiveDataExtKtTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testDispatcher = TestCoroutineDispatcher()
    @ExperimentalCoroutinesApi
    val testScope = TestCoroutineScope(testDispatcher)


    @Rule
    @JvmField
    val rxRule = RxSchedulerRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun testDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun testAsLiveData() {
        asyncToSync()
        val observer = mock<Observer<Resource<Int>>>()
        var emitter: FlowableEmitter<Int>? = null
        val flowable = Flowable.create(FlowableOnSubscribe<Int> {
            emitter = it
        }, BackpressureStrategy.BUFFER)

        flowable.asLiveData().observeForever(observer)
        Mockito.verify(observer).onChanged(Resource.loading(null))

        reset(observer)
        emitter?.onNext(1)
        Mockito.verify(observer).onChanged(Resource.success(1))

        reset(observer)
        val throwable = Throwable()
        emitter?.onError(throwable)
        Mockito.verify(observer).onChanged(Resource.error(throwable, null))

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testResourceLiveData() = testScope.runBlockingTest {
        val subject = resourceLiveData{
            delay(1000)
            "test"
        }
        subject.observeForTesting {
            assertEquals(Resource.loading(null),subject.value)
            advanceTimeBy(1000)
            assertEquals(Resource.success("test"),subject.value)

        }
    }
}