package com.moi.lime.ui.home.recommend

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.moi.lime.vo.MusicInformation

class RecommendViewPagerAdapter(
        fragmentManager: FragmentManager,
        dataList: List<MusicInformation>
) : FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = dataList.map { RecommendItemFragment.newInstance(it) }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}