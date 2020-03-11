package com.moi.lime.ui.home

import androidx.fragment.app.Fragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.lime.testing.TestFragment
import com.moi.lime.R
import com.moi.lime.util.DataBindingIdlingResourceRule
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)


    @Before
    fun setUp() {
        val homeFragment = TestHomeFragment()
        activityRule.activity.setFragment(homeFragment)
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    @Test
    fun testInitialDisplay() {
        onView(withText("home")).check(matches(isDisplayed()))
        onView(withText("profile")).check(matches(not(isDisplayed())))
    }

    @Test
    fun testTabClick() {
        onView(withId(R.id.personImage)).perform(click())
        onView(withText("profile")).check(matches(isDisplayed()))
        onView(withText("home")).check(matches(not(isDisplayed())))


        onView(withId(R.id.homeImage)).perform(click())
        onView(withText("home")).check(matches(isDisplayed()))
        onView(withText("profile")).check(matches(not(isDisplayed())))

    }

    class TestHomeFragment : HomeFragment() {
        override fun getFragments(): List<Fragment> {
            return listOf(TestFragment.newInstance("home"), TestFragment.newInstance("profile"))
        }
    }
}