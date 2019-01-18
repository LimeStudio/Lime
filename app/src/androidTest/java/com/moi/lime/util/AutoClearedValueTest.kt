package com.moi.lime.util

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AutoClearedValueTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    private lateinit var testFragment: TestFragment

    @Before
    fun setUp(){
        testFragment = TestFragment()
        activityRule.activity.setFragment(testFragment)
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    @Test
    fun clearOnReplace() {
        testFragment.testValue = "foo"
        activityRule.activity.replaceFragment(TestFragment())
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        try {
            testFragment.testValue
            Assert.fail("should throw if accessed when not set")
        } catch (ex: IllegalStateException) {
        }
    }

    @Test
    fun dontClearForChildFragment() {
        testFragment.testValue = "foo"
        testFragment.childFragmentManager.beginTransaction()
                .add(Fragment(), "foo").commit()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        MatcherAssert.assertThat(testFragment.testValue, Matchers.`is`("foo"))
    }

    @Test
    fun dontClearForDialog() {
        testFragment.testValue = "foo"
        val dialogFragment = DialogFragment()
        dialogFragment.show(testFragment.fragmentManager!!, "dialog")
        dialogFragment.dismiss()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
        MatcherAssert.assertThat(testFragment.testValue, Matchers.`is`("foo"))
    }

    class TestFragment : Fragment() {
        var testValue by autoCleared<String>()
    }
}