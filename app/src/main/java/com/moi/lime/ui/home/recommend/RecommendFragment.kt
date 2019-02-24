package com.moi.lime.ui.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.moi.lime.R
import com.moi.lime.databinding.FragmentRecommendBinding

class RecommendFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentRecommendBinding>(
                inflater,
                R.layout.fragment_recommend,
                container,
                false
        )
        dataBinding.recommendViewPager.offscreenPageLimit = 1
        dataBinding.adapter = RecommendViewPagerAdapter(childFragmentManager, listOf("", "", "", "", ""))
        return dataBinding.root
    }
}