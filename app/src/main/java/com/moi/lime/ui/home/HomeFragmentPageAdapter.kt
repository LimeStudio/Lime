package com.moi.lime.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeFragmentPageAdapter(fragmentManager: FragmentManager, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size
}