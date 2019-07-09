package com.moi.lime.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.moi.lime.R
import com.moi.lime.databinding.FragmentProfilePlayListItemViewBinding
import com.moi.lime.util.autoCleared
import com.moi.lime.util.toBean
import com.moi.lime.util.toJson
import com.moi.lime.vo.PlaylistItem

class ProfilePlayListItemFragment : Fragment() {

    companion object {
        private const val KEY_PROFILE_PLAYLIST_ITEM = "ProfilePlayListItemFragment.key"
        fun newInstance(userPlayListItem: PlaylistItem): ProfilePlayListItemFragment {
            val json = userPlayListItem.toJson()
            val bundle = Bundle().apply {
                putString(KEY_PROFILE_PLAYLIST_ITEM, json)
            }
            val fragment = ProfilePlayListItemFragment()
            fragment.arguments = bundle
            return fragment

        }
    }

    private val userPlayListItem: PlaylistItem? by lazy(LazyThreadSafetyMode.NONE) {
        val json = arguments?.getString(KEY_PROFILE_PLAYLIST_ITEM)
        json?.toBean<PlaylistItem>()
    }

    private var binding by autoCleared<FragmentProfilePlayListItemViewBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_play_list_item_view, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.playlistItem = userPlayListItem
    }
}