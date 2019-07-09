package com.moi.lime.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lime.testing.OpenForTesting
import com.moi.lime.R
import com.moi.lime.databinding.FragmentProfileBinding
import com.moi.lime.di.Injectable
import com.moi.lime.ui.LimeFragmentPageAdapter
import com.moi.lime.ui.callback.ViewClickCallback
import com.moi.lime.util.autoCleared
import com.moi.lime.vo.PlaylistItem
import com.moi.lime.vo.Status
import javax.inject.Inject

@OpenForTesting
class ProfileFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory)
                .get(ProfileFragmentViewModel::class.java)
    }
    private var binding by autoCleared<FragmentProfileBinding>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.onViewClick = object : ViewClickCallback {
            override fun click(view: View) {
                viewModel.fetchPlaylistsTrigger.value = Unit
            }

        }
        viewModel.profilePlaylists.observe(this, Observer {
            if (it.status == Status.SUCCESS) {
                initViewPager(it.data)
            }
        })

    }

    private fun initViewPager(list: List<PlaylistItem>?) {
        list?.let { playlistList ->
            val adapter = LimeFragmentPageAdapter(childFragmentManager, getFragment(playlistList))
            binding.viewPager.offscreenPageLimit = 1
            binding.adapter = adapter
        }
    }

    fun getFragment(list: List<PlaylistItem>): List<Fragment> = list.map { ProfilePlayListItemFragment.newInstance(it) }

}