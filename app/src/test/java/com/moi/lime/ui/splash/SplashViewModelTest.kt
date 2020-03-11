package com.moi.lime.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.core.user.UserManager
import com.moi.lime.util.TestDispatchers
import com.moi.lime.util.TestDispatchersRule
import com.moi.lime.util.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SplashViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()

    private val userManager = mock<UserManager>()
    private val viewModel = SplashViewModel(userManager,TestDispatchers(testDispatchersRule.testDispatcher))

    @Test
    fun testInitWithSignIn() = testDispatchersRule.testScope.runBlockingTest {
        Mockito.`when`(userManager.isSignIn()).thenReturn(true)
        viewModel.init()
        advanceTimeBy(2000)
        assertTrue {
            viewModel.isSignInValue.value==true
        }
    }

    @Test
    fun testInitWithSignOut() = testDispatchersRule.testScope.runBlockingTest {
        Mockito.`when`(userManager.isSignIn()).thenReturn(false)
        viewModel.init()
        advanceTimeBy(2000)
        assertFalse {
            viewModel.isSignInValue.value==true
        }
    }
}