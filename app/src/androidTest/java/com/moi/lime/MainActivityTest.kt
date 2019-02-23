package com.moi.lime

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.moi.lime.core.rxjava.RxBus
import com.moi.lime.util.RxSchedulerRule
import com.moi.lime.vo.SignInExpireEvent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(TestMainActivity::class.java, true, true)

    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    @Test
    fun testSignInExpired(){
        RxBus.INSTANCE.post(SignInExpireEvent())
        Mockito.verify(activityRule.activity.navController).navigate(MainNavDirections.actionGlobalSignInFragment())
    }
}