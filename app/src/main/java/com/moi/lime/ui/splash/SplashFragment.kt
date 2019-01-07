package com.moi.lime.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.moi.lime.R
import com.moi.lime.di.Injectable
import com.moi.lime.util.Logger
import javax.inject.Inject

class SplashFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory)
                .get(SplashViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.isSignInValue.observe(this, Observer {
            if (it == null) return@Observer
            if (it) {
                Navigation.findNavController(view!!)
                        .navigate(R.id.go_to_first_fragment_from_splash)

            } else {
                Navigation.findNavController(view!!)
                        .navigate(R.id.go_to_sign_in_fragment_from_splash)
            }
        })
        viewModel.init()

    }
}