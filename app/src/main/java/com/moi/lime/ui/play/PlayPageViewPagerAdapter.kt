package com.moi.lime.ui.play

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PlayPageViewPagerAdapter(fragmentManager: FragmentManager, urlList: List<String>)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = urlList.map {
        PlayPageItemFragment.newInstance(it)
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

}