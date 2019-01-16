package com.moi.lime.ui.splash

import android.util.Log
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
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
    fun testJump() {
        isSignInValue.postValue(true)
        //  verify(splashFragment.navController).navigate(R.id.go_to_first_fragment_from_splash)
        verify(viewModel).init()
        //  onView(withId(R.id.logo)).check(ViewAssertions.matches(isDisplayed()))
    }

    class TestSplashFragment : SplashFragment() {
        val navController = mock<NavController>()
        override fun navController() = navController
    }

}