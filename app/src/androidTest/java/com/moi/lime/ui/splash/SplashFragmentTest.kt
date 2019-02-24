package com.moi.lime.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.moi.lime.util.DataBindingIdlingResourceRule
import com.moi.lime.util.TaskExecutorWithIdlingResourceRule
import com.moi.lime.util.ViewModelUtil
import com.moi.lime.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class SplashFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    private lateinit var viewModel: SplashViewModel

    private val isSignInValue = MutableLiveData<Boolean>()

    private val splashFragment = TestSplashFragment()

    @Before
    fun init() {
        viewModel = mock()
        Mockito.`when`(viewModel.isSignInValue).thenReturn(isSignInValue)
        splashFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(splashFragment)
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    @Test
    fun testJumpSignIn() {
        isSignInValue.postValue(false)
        Thread.sleep(500)
        verify(splashFragment.navController).navigate(SplashFragmentDirections.goToSignInFragmentFromSplash())
    }

    @Test
    fun testJumpHome() {
        isSignInValue.postValue(true)
        Thread.sleep(500)
        verify(splashFragment.navController).navigate(SplashFragmentDirections.goToHomeFragmentFromSplash())
    }

    @Test
    fun testCallInit(){
        verify(viewModel).init()
    }

    class TestSplashFragment : SplashFragment() {
        val navController = mock<NavController>()
        override fun navController() = navController
    }

}