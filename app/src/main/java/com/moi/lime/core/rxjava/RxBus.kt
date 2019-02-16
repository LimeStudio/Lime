package com.moi.lime.core.rxjava

import io.reactivex.Flowable
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor

enum class RxBus {
    INSTANCE;

    val bus: FlowableProcessor<Any> = PublishProcessor.create()
    fun post(any: Any) = bus.onNext(any)
    inline fun <reified T> toFlowable(): Flowable<T> = bus.ofType(T::class.java)
}