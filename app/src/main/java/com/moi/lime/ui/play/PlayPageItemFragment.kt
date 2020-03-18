package com.moi.lime.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.moi.lime.R
import com.moi.lime.databinding.FragmentPlayPageViewPagerItemBinding
import com.moi.lime.util.autoCleared

class PlayPageItemFragment : Fragment() {
    companion object {
        private const val KEY_IMAGE_URL = "PlayPageItemFragment.url.key"
        fun newInstance(musicUrl: String) = PlayPageItemFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_IMAGE_URL, musicUrl)
            }
        }
    }

    private var binding by autoCleared<FragmentPlayPageViewPagerItemBinding>()

    private val url by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getString(KEY_IMAGE_URL, "") ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_play_page_view_pager_item, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.url = url
    }
}