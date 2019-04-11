package com.moi.lime.ui.home.recommend

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lime.testing.SingleFragmentActivity
import com.moi.lime.ui.home.recommend.LoadingRecommendSwitcher.Companion.RECOMMEND_DAY_KEY
import com.moi.lime.vo.Resource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoadingRecommendSwitcherTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    private val loadingRecommendSwitcher = LoadingRecommendSwitcher(ApplicationProvider.getApplicationContext(), 6)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val sp = ApplicationProvider.getApplicationContext<Context>().getSharedPreferences("lime", Context.MODE_PRIVATE)


    @Test
    fun testFirstLoading() {
        sp.edit().apply {
            clear()
            commit()
        }
        assertThat(true,not(loadingRecommendSwitcher.isShouldFetchFromDb(1554795140000)))
    }

    @Test
    fun testLoadingFromDb() {

        sp.edit().apply {
            putLong(RECOMMEND_DAY_KEY, 1554782461000)
            commit()
        }
        assertThat(true,`is`(loadingRecommendSwitcher.isShouldFetchFromDb(1554795140000)))

    }

    @Test
    fun testLoadingFromDbNextDay() {


        sp.edit().apply {
            putLong(RECOMMEND_DAY_KEY, 1554782461000)
            commit()
        }
        assertThat(true,`is`(loadingRecommendSwitcher.isShouldFetchFromDb(1554829261000)))
    }

    @Test
    fun testLoadingFromNetWorkNextDay() {

        sp.edit().apply {
            putLong(RECOMMEND_DAY_KEY, 1554782461000)
            commit()
        }

        assertThat(true,not(loadingRecommendSwitcher.isShouldFetchFromDb(1554847261000)))
    }

    @Test
    fun testBindRecommendResource() {
        sp.edit().apply {
            clear()
            commit()
        }

        val liveData = MutableLiveData<Resource<*>>()
        loadingRecommendSwitcher.bindRecommendResource(activityRule.activity, liveData)
        liveData.value = Resource.success(null)
        assertTrue(sp.getLong(RECOMMEND_DAY_KEY, -1) > 1)

    }

    @After
    fun after() {
        sp.edit().apply {
            clear()
            commit()
        }
    }
}