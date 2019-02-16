package com.moi.lime.core.rxjava

import com.moi.lime.util.RxSchedulerRule
import io.reactivex.subscribers.TestSubscriber
import org.junit.Rule
import org.junit.Test

class RxBusTest {
    @Rule
    @JvmField
    val rxRule = RxSchedulerRule()

    @Test
    fun testRxBus() {
        val testSubscriber = TestSubscriber<String>()
        RxBus.INSTANCE.toFlowable<String>()
                .subscribe (testSubscriber)
        RxBus.INSTANCE.post("test")
        testSubscriber.assertValue("test")
    }
}