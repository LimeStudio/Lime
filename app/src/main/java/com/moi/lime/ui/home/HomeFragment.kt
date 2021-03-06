package com.moi.lime.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lime.testing.OpenForTesting
import com.moi.lime.MainNavDirections
import com.moi.lime.R
import com.moi.lime.databinding.FragmentHomeBinding
import com.moi.lime.di.Injectable
import com.moi.lime.ui.LimeFragmentPageAdapter
import com.moi.lime.ui.callback.ViewClickCallback
import com.moi.lime.ui.home.profile.ProfileFragment
import com.moi.lime.ui.home.recommend.RecommendFragment
import com.moi.lime.util.autoCleared

@OpenForTesting
class HomeFragment : Fragment(), Injectable {

    var binding by autoCleared<FragmentHomeBinding>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentHomeBinding>(
                inflater,
                R.layout.fragment_home,
                container,
                false
        )
        dataBinding.viewClick = object : ViewClickCallback {
            override fun click(view: View) {
                when (view.id) {
                    R.id.homeImage -> {
                        dataBinding.viewPager.setCurrentItem(0, false)
                    }
                    R.id.personImage -> {
                        dataBinding.viewPager.setCurrentItem(1, false)
                    }
                    R.id.floatingButton -> {
                        findNavController().navigate(MainNavDirections.actionGlobalSignInFragment())
                    }
                }
            }
        }
        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val fragmentList = getFragments()
        val fragmentAdapter = LimeFragmentPageAdapter(childFragmentManager, fragmentList)
        binding.viewPager.adapter = fragmentAdapter
    }

    /**
     * Created to be able to override in tests
     */
    fun getFragments(): List<Fragment> = listOf(RecommendFragment(), ProfileFragment())
}