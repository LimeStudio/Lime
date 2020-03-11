package com.moi.lime

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule =ActivityTestRule(TestMainActivity::class.java, true, true)


    @Test
    fun testSignInExpired() {
        activityRule.activity.signInExpireInterceptor.onLoginExpire()
        Mockito.verify(activityRule.activity.navController).navigate(MainNavDirections.actionGlobalSignInFragment())
    }
}