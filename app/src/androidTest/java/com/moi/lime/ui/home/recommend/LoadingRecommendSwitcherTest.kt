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

    @Test
    fun testIsShouldFetchFromDb() {
        val sp = ApplicationProvider.getApplicationContext<Context>().getSharedPreferences("lime", Context.MODE_PRIVATE)

        sp.edit().apply {
            clear()
            commit()
        }
        assertTrue(!loadingRecommendSwitcher.isShouldFetchFromDb(1554795140000))

        sp.edit().apply {
            putLong(RECOMMEND_DAY_KEY, 1554782461000)
            commit()
        }
        assertTrue(loadingRecommendSwitcher.isShouldFetchFromDb(1554795140000))

        sp.edit().apply {
            putLong(RECOMMEND_DAY_KEY, 1554782461000)
            commit()
        }
        assertTrue(loadingRecommendSwitcher.isShouldFetchFromDb(1554829261000))

        sp.edit().apply {
            putLong(RECOMMEND_DAY_KEY, 1554782461000)
            commit()
        }
        assertTrue(!loadingRecommendSwitcher.isShouldFetchFromDb(1554847261000))

    }

    @Test
    fun testBindRecommendResource() {
        val sp = ApplicationProvider.getApplicationContext<Context>().getSharedPreferences("lime", Context.MODE_PRIVATE)

        sp.edit().apply {
            clear()
            commit()
        }

        val liveData = MutableLiveData<Resource<*>>()
        loadingRecommendSwitcher.bindRecommendResource(activityRule.activity, liveData)
        liveData.value = Resource.success(null)
        assertTrue(sp.getLong(RECOMMEND_DAY_KEY, -1) > 1)

    }
}