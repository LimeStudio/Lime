package com.moi.lime.ui.home

import androidx.fragment.app.FragmentManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moi.lime.ui.home.profile.ProfileFragment
import com.moi.lime.ui.home.recommend.RecommendFragment
import com.moi.lime.util.mock
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentPageAdapterTest {
    private val recommendFragment = RecommendFragment()
    private val profileFragment = ProfileFragment()
    private lateinit var adapter: HomeFragmentPageAdapter
    @Before
    fun setUp() {
        val fragmentList = listOf(recommendFragment, profileFragment)
        val fm = mock<FragmentManager>()
        adapter = HomeFragmentPageAdapter(fm, fragmentList)
    }

    @Test
    fun testGetItem() {
        val fragment0 = adapter.getItem(0)
        assertEquals(recommendFragment, fragment0)

        val fragment1 = adapter.getItem(1)
        assertEquals(profileFragment, fragment1)
    }

    @Test
    fun testGetCount() {
        assertEquals(2, adapter.count)
    }
}