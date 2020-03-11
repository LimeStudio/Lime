package com.moi.lime.ui.home.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.lime.testing.TestFragment
import com.moi.lime.R
import com.moi.lime.core.livedata.SingleLiveEvent
import com.moi.lime.util.*
import com.moi.lime.vo.PlaylistItem
import com.moi.lime.vo.Resource
import com.moi.lime.vo.UserPlayLists
import okio.Okio
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val fragment = TestProfileFragment()

    private val viewModel: ProfileFragmentViewModel = mock()

    private val profile = createProfile(true)

    private val profilePlaylists = MutableLiveData<Resource<List<PlaylistItem>>>()

    private val fetchPlaylistsTrigger = SingleLiveEvent<Unit>()


    @Before
    fun setUp() {
        `when`(viewModel.fetchPlaylistsTrigger).thenReturn(fetchPlaylistsTrigger)
        `when`(viewModel.profilePlaylists).thenReturn(profilePlaylists)
        `when`(viewModel.profile).thenReturn(profile)
        fragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(fragment)
        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun testUserInfo() {
        onView(withId(R.id.textName)).check(matches(withText("test")))
        onView(withId(R.id.textCity)).check(matches(withText("1")))
        onView(withId(R.id.followers_count)).check(matches(withText("1")))
        onView(withId(R.id.following_count)).check(matches(withText("1")))
    }

    @Test
    fun testError() {
        profilePlaylists.value = Resource.error(null, null)
        onView(withId(R.id.retry_button)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.contentLoadingProgressBar)).check(matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.retry_button)).perform(ViewActions.click())

        fetchPlaylistsTrigger.observeForTesting {
            assertEquals(Unit, fetchPlaylistsTrigger.value)
        }

    }

    @Test
    fun testLoading(){
        profilePlaylists.value = Resource.loading(null)
        onView(withId(R.id.retry_button)).check(matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.contentLoadingProgressBar)).check(matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun testLoadSuccess(){
        val userPlayLists = loadJsonFromFilePath("/json/UserPlayListJson",javaClass).toBean<UserPlayLists>()
        profilePlaylists.value = Resource.success(userPlayLists!!.playlist)
        onView(withId(R.id.retry_button)).check(matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.contentLoadingProgressBar)).check(matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(withText("PlayListItemFragment")).check(matches(ViewMatchers.isDisplayed()))

    }

    class TestProfileFragment : ProfileFragment() {
        override fun getFragment(list: List<PlaylistItem>): List<Fragment> {
            return listOf(TestFragment.newInstance("PlayListItemFragment"))
        }
    }
}