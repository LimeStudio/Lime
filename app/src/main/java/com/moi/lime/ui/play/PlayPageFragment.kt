package com.moi.lime.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.moi.lime.R
import com.moi.lime.databinding.FragmentPlayPageBinding
import com.moi.lime.di.Injectable
import com.moi.lime.util.autoCleared
import com.moi.lime.viewmodel.PlayPageViewModelFactory
import javax.inject.Inject

class PlayPageFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: PlayPageViewModelFactory

    private val args: PlayPageFragmentArgs by navArgs()

    private val viewModel by viewModels<PlayPageViewModel> {
        viewModelFactory.apply {
            currentId = args.musicId
        }
    }
    var binding by autoCleared<FragmentPlayPageBinding>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentPlayPageBinding>(
                inflater,
                R.layout.fragment_play_page,
                container,
                false
        )
        binding = dataBinding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}