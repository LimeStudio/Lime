package com.moi.lime.ui.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.moi.lime.R
import com.moi.lime.databinding.FragmentRecommendItemBinding
import com.moi.lime.di.Injectable
import com.moi.lime.util.autoCleared
import com.moi.lime.util.toBean
import com.moi.lime.util.toJson
import com.moi.lime.vo.MusicInformation

class RecommendItemFragment : Fragment(), Injectable {

    companion object {
        private const val KEY_RECOMMEND_ITEM = "RecommendItemFragment.key"
        fun newInstance(musicInformation: MusicInformation): RecommendItemFragment {
            val json = musicInformation.toJson()
            val bundle = Bundle().apply {
                putString(KEY_RECOMMEND_ITEM, json)
            }
            val fragment = RecommendItemFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var binding by autoCleared<FragmentRecommendItemBinding>()

    private val musicInformation: MusicInformation? by lazy(LazyThreadSafetyMode.NONE) {
        val json = arguments?.getString(KEY_RECOMMEND_ITEM)
        json?.toBean<MusicInformation>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_recommend_item, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.music = musicInformation?.limeMusic
        binding.album =musicInformation?.limeAlbum?.firstOrNull()
        binding.artist = musicInformation?.limeArtist?.firstOrNull()
    }
}