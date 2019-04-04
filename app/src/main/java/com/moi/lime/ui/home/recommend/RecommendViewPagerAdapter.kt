package com.moi.lime.ui.home.recommend

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class RecommendViewPagerAdapter(
        fragmentManager: FragmentManager,
        dataList: List<String>
) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = dataList.map { RecommendItemFragment() }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}