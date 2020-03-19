package com.moi.lime.ui.play

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.moi.lime.R
import com.moi.lime.databinding.FragmentPlayPageBinding
import com.moi.lime.di.Injectable
import com.moi.lime.util.autoCleared
import com.moi.lime.viewmodel.PlayPageViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayPageFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: PlayPageViewModelFactory

    private val args: PlayPageFragmentArgs by navArgs()

    private val viewModel by viewModels<PlayPageViewModel> {
        viewModelFactory.apply {
            currentId = MutableLiveData(args.musicId)
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
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                viewModel.currentMusicIdChange(position)
                changeBackground(position)
            }

        })
        viewModel.playPageDataLists.observe(this, Observer { list ->
            val urls = list.map {
                it.musicImageUrl
            }
            val adapter = PlayPageViewPagerAdapter(childFragmentManager, urls)
            val currentPosition = list.indexOfFirst {
                it.id == viewModel.currentMusicId.value
            }
            binding.viewPager.adapter = adapter
            binding.viewPager.setCurrentItem(currentPosition, false)
            if (currentPosition == 0) {
                changeBackground(0)
            }
        })

    }

    private fun changeBackground(position: Int) {
        val url = viewModel.getMusicImageUrlByPosition(position)
        if (url != null) {
            Glide.with(context!!)
                    .asBitmap()
                    .load(url)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onLoadCleared(placeholder: Drawable?) {
                        }

                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            val palette = Palette.from(resource)
                            palette.generate { pt ->
                                val color = pt?.getDominantColor(Color.DKGRAY) ?: Color.DKGRAY
                                viewModel.backgroundColor.value = color
                                val swatch = pt?.dominantSwatch
                                val textColor = swatch?.bodyTextColor ?: Color.WHITE
                                viewModel.textColor.value = textColor
                            }
                        }

                    })
        }

    }
}