package com.moi.lime.ui.home.recommend

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.moi.lime.R
import com.moi.lime.core.livedata.SingleLiveEvent
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.*
import com.moi.lime.vo.MusicInformation
import com.moi.lime.vo.Resource
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class RecommendFragmentTest : LimeDbTest() {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    private val fragment = RecommendFragment()

    private val viewModel: RecommendFragmentViewModel = mock()

    private val trigger = SingleLiveEvent<Boolean>()
    private val resource = MutableLiveData<Resource<List<MusicInformation>>>()


    @Before
    fun setUp() {
        `when`(viewModel.fetchRecommendTrigger).thenReturn(trigger)
        `when`(viewModel.recommendResource).thenReturn(resource)
        fragment.loadingRecommendSwitcher = LoadingRecommendSwitcher(ApplicationProvider.getApplicationContext(), 6)
        fragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(fragment)
    }

    @Test
    fun testInit() {
        Thread.sleep(500)
        val observer = mock<Observer<Boolean>>()
        trigger.observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.anyBoolean())
    }

    @Test
    fun testError() {
        resource.value = Resource.error(null, null)
        onView(withId(R.id.retry_button)).check(matches(isDisplayed()))
        onView(withId(R.id.contentLoadingProgressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.retry_button)).perform(ViewActions.click())

        val observer = mock<Observer<Boolean>>()
        trigger.observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.anyBoolean())

    }

    @Test
    fun testLoading() {
        resource.value = Resource.loading(null)
        onView(withId(R.id.retry_button)).check(matches(not(isDisplayed())))
        onView(withId(R.id.contentLoadingProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun testLoaded() {

        MusicMapper(MusicEntityCreator.createRecommendMusicEntity(), MusicEntityCreator.createMusicUrlsEntity())
                .saveMusic(db)
        val musicInformation = db.musicInformationDao().getAllMusicInformation().test().values().first().first()
        resource.value = Resource.success(listOf(musicInformation))
        onView(withId(R.id.recommend_item_root)).check(matches(isDisplayed()))

    }

}