package com.moi.lime.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LimeFragmentPageAdapter(fragmentManager: FragmentManager, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size
}