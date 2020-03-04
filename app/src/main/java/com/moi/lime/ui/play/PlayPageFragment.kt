package com.moi.lime.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.moi.lime.R
import com.moi.lime.databinding.FragmentPlayPageBinding
import com.moi.lime.di.Injectable
import com.moi.lime.util.autoCleared

class PlayPageFragment : Fragment(), Injectable {
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
}