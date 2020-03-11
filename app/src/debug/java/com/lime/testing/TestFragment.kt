package com.lime.testing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moi.lime.R
import kotlinx.android.synthetic.debug.fragment_test.*

class TestFragment : Fragment() {
    private val testTag by lazy {
        arguments?.getString(TEST_FRAGMENT_KEY) ?: ""
    }

    companion object {
        private const val TEST_FRAGMENT_KEY = "TestFragment.key"
        fun newInstance(tag: String): TestFragment {
            val fragment = TestFragment()
            fragment.arguments = Bundle().apply {
                putString(TEST_FRAGMENT_KEY, tag)
            }
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text_tag.text = testTag
    }
}