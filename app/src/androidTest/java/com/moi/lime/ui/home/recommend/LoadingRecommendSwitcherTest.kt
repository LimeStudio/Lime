package com.moi.lime.ui.home.recommend

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.moi.lime.util.mock
import com.moi.lime.vo.Resource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class LoadingRecommendSwitcherTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    private val edit: SharedPreferences.Editor = mock()

    private lateinit var loadingRecommendSwitcher: LoadingRecommendSwitcher

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val sp = mock<SharedPreferences>()

    @Before
    fun setUp() {

        `when`(sp.edit()).thenReturn(edit)
        loadingRecommendSwitcher = LoadingRecommendSwitcher(sp, 6)
    }

    @Test
    fun testFirstLoading() {
        `when`(sp.getLong(ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())).thenReturn(-1L)
        assertThat(true, not(loadingRecommendSwitcher.isShouldFetchFromDb(1554795140000)))
    }

    @Test
    fun testLoadingFromDb() {
        `when`(sp.getLong(ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())).thenReturn(1554782461000L)
        assertThat(true, `is`(loadingRecommendSwitcher.isShouldFetchFromDb(1554795140000)))
    }

    @Test
    fun testLoadingFromDbNextDay() {
        `when`(sp.getLong(ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())).thenReturn(1554782461000L)
        assertThat(true, `is`(loadingRecommendSwitcher.isShouldFetchFromDb(1554829261000)))
    }

    @Test
    fun testLoadingFromNetWorkNextDay() {
        `when`(sp.getLong(ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())).thenReturn(1554782461000L)
        assertThat(true, not(loadingRecommendSwitcher.isShouldFetchFromDb(1554847261000)))
    }

    @Test
    fun testBindRecommendResource() {
        `when`(sp.getLong(ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())).thenReturn(-1L)
        val liveData = MutableLiveData<Resource<*>>()
        loadingRecommendSwitcher.bindRecommendResource(activityRule.activity, liveData)
        liveData.value = Resource.success(null)
        verify(edit).putLong(ArgumentMatchers.anyString(), ArgumentMatchers.anyLong())

    }

}