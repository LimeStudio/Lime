package com.moi.lime.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lime.testing.OpenForTesting
import com.moi.lime.R
import com.moi.lime.databinding.FragmentSignInBinding
import com.moi.lime.di.Injectable
import com.moi.lime.util.Logger
import com.moi.lime.util.autoCleared
import com.moi.lime.vo.Resource
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

@OpenForTesting
class SignInFragment : Fragment(), Injectable {

    var binding by autoCleared<FragmentSignInBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_sign_in,
                container,
                false
        )
        binding.lifecycleOwner = this
        binding.signInViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginResource.observe(this, Observer {
            if (it?.data == true) {
                navController()
                        .navigate(SignInFragmentDirections.goToHomeFragmentFromSign())
            }
        })
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}