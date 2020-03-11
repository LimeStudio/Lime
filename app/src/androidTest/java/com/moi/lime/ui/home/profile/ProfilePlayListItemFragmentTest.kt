package com.moi.lime.ui.home.profile

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.moi.lime.R
import com.moi.lime.util.DataBindingIdlingResourceRule
import com.moi.lime.util.loadJsonFromFilePath
import com.moi.lime.util.toBean
import com.moi.lime.vo.UserPlayLists
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfilePlayListItemFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    private lateinit var fragment: ProfilePlayListItemFragment

    private val itemData = loadJsonFromFilePath("/json/UserPlayListJson", javaClass)
            .toBean<UserPlayLists>()!!.playlist!!.first()

    @Before
    fun setUp() {
        fragment = ProfilePlayListItemFragment.newInstance(itemData)
        activityRule.activity.setFragment(fragment)
    }

    @Test
    fun testUI(){
        onView(withId(R.id.playlist_name)).check(matches(withText(itemData.name)))
        onView(withId(R.id.text_description)).check(matches(withText(itemData.description)))
    }

}