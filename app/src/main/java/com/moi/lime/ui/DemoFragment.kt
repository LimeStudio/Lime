package com.moi.lime.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moi.lime.R
import com.moi.lime.databinding.ActivityDemoBinding
import com.moi.lime.databinding.FragmentHomeBinding
import com.moi.lime.di.Injectable
import com.moi.lime.ui.signin.SignInViewModel
import com.moi.lime.util.autoCleared
import javax.inject.Inject

class DemoFragment : Fragment(), Injectable {

    var binding by autoCleared<ActivityDemoBinding>()


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory).get(DemoFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<ActivityDemoBinding>(
                inflater,
                R.layout.activity_demo,
                container,
                false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.button2.setOnClickListener { //添加商品按钮点击
            viewModel.addProductEvent.value = "id"
        }
        binding.button.setOnClickListener { //添加商品按钮点击
            viewModel.addProductToCartEvent.value = "id"
        }
        return binding.root
    }
}