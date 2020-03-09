package com.moi.lime.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moi.lime.R
import com.moi.lime.databinding.FragmentPlayPageBinding
import com.moi.lime.di.Injectable
import com.moi.lime.util.autoCleared
import javax.inject.Inject

class PlayPageFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory)
                .get(PlayPageFragmentViewModel::class.java)
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
        viewModel.toString()
        return binding.root
    }
}