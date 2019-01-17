package com.moi.lime.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.moi.lime.R
import com.moi.lime.util.*
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

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    private lateinit var viewModel: SplashViewModel

    private val isSignInValue = MutableLiveData<Boolean>()

    private val splashFragment = TestSplashFragment()

    @Before
    fun init() {
        viewModel = mock()
        Mockito.`when`(viewModel.isSignInValue).thenReturn(isSignInValue)
        splashFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(splashFragment)
        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun testJumpSignIn() {
        isSignInValue.postValue(false)
        onView(withId(R.id.logo)).check(matches(isDisplayed()))
        verify(splashFragment.navController).navigate(SplashFragmentDirections.goToSignInFragmentFromSplash())
    }

    @Test
    fun testJumpHome() {
        isSignInValue.postValue(true)
        onView(withId(R.id.logo)).check(matches(isDisplayed()))
        verify(splashFragment.navController).navigate(SplashFragmentDirections.goToHomeFragmentFromSplash())
    }

    class TestSplashFragment : SplashFragment() {
        val navController = mock<NavController>()
        override fun navController() = navController
    }

}