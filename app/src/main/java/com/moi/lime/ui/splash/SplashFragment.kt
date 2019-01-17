package com.moi.lime.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.lime.testing.OpenForTesting
import com.moi.lime.R
import com.moi.lime.databinding.FragmentSplashBinding
import com.moi.lime.di.Injectable
import javax.inject.Inject

@OpenForTesting
class SplashFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory)
                .get(SplashViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentSplashBinding>(
                inflater,
                R.layout.fragment_splash,
                container,
                false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isSignInValue.observe(this, Observer {
            if (it == null) return@Observer
            if (true) {
                navController()
                        .navigate(SplashFragmentDirections.goToHomeFragmentFromSplash())
            } else {
                navController()
                        .navigate(SplashFragmentDirections.goToSignInFragmentFromSplash())
            }
        })
        viewModel.init()
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}