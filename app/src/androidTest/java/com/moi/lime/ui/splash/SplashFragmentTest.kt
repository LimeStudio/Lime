package com.moi.lime.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.moi.lime.R
import com.moi.lime.util.EspressoTestUtil
import com.moi.lime.util.ViewModelUtil
import com.moi.lime.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.matches
import org.mockito.Mockito.verify

class SplashFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SplashViewModel

    private val isSignInValue = MutableLiveData<Boolean>()

    private val splashFragment = TestSplashFragment()

    @Before
    fun init() {
        viewModel = mock()
        Mockito.`when`(viewModel.isSignInValue).thenReturn(isSignInValue)
        splashFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(splashFragment)
//EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun testJump() {
//        isSignInValue.value=true
//        verify(splashFragment.navController).navigate(R.id.go_to_first_fragment_from_splash)
       // verify(viewModel).init()
        onView(withId(R.id.logo)).check(ViewAssertions.matches(isDisplayed()))
    }

    class TestSplashFragment : SplashFragment() {
        val navController = mock<NavController>()
        override fun navController() = navController
    }

}