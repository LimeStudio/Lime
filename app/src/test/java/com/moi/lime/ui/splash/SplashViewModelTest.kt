package com.moi.lime.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moi.lime.core.user.UserManager
import com.moi.lime.util.RxSchedulerRule
import com.moi.lime.util.mock
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class SplashViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private val userManager = mock<UserManager>()
    private val viewModel = SplashViewModel(userManager)

    @Test
    fun testInitWithSignIn() {
        Mockito.`when`(userManager.isSignIn()).thenReturn(true)
        val observer = mock<Observer<Boolean>>()
        viewModel.isSignInValue.observeForever(observer)
        viewModel.init()
        Mockito.verify(observer).onChanged(true)
    }

    @Test
    fun testInitWithSignOut() {
        Mockito.`when`(userManager.isSignIn()).thenReturn(false)
        val observer = mock<Observer<Boolean>>()
        viewModel.isSignInValue.observeForever(observer)
        viewModel.init()
        Mockito.verify(observer).onChanged(false)
    }
}