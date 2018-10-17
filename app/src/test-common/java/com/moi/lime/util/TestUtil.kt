package com.moi.lime.util

import com.moi.lime.vo.Profile
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

fun createProfile(isSignIn: Boolean = false): Profile {
    return Profile(
            isSignIn,
            "123456",
            1234556,
            1,
            "test",
            "1",
            123456,
            "1",
            true,
            "1",
            "1")
}

fun asyncToSync() {
    RxJavaPlugins.reset()
    RxJavaPlugins.setIoSchedulerHandler {
        return@setIoSchedulerHandler Schedulers.trampoline()
    }
}