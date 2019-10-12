package com.moi.lime.ui.home.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moi.lime.R
import com.moi.lime.databinding.FragmentRecommendBinding
import com.moi.lime.di.Injectable
import com.moi.lime.ui.callback.ViewClickCallback
import com.moi.lime.util.autoCleared
import javax.inject.Inject

class RecommendFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var loadingRecommendSwitcher: LoadingRecommendSwitcher

    private var binding by autoCleared<FragmentRecommendBinding>()


    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory)
                .get(RecommendFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recommend, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.onViewClick = object : ViewClickCallback {
            override fun click(view: View) {
                viewModel.fetchRecommendTrigger.value = loadingRecommendSwitcher.isShouldFetchFromDb(System.currentTimeMillis())
            }
        }
        binding.musicInformation = viewModel.recommendResource
        loadingRecommendSwitcher.bindRecommendResource(this, viewModel.recommendResource)
        viewModel.recommendResource.observe(this, Observer { resource ->
            resource.data?.let {
                binding.recommendViewPager.offscreenPageLimit = 1
                binding.adapter = RecommendViewPagerAdapter(childFragmentManager, it)
            }
        })
        viewModel.fetchRecommendTrigger.value = loadingRecommendSwitcher.isShouldFetchFromDb(System.currentTimeMillis())
    }
}