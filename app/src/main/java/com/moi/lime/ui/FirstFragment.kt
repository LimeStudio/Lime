package com.moi.lime.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.moi.lime.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?): Unit = view!!.let { view ->
        super.onActivityCreated(savedInstanceState)
        button.setOnClickListener {
            val fragmentArgs = SecondFragmentArgs.Builder("dadada").build()
            Navigation.findNavController(view)
                    .navigate(R.id.go_to_second_fragment_from_first, fragmentArgs.toBundle())
        }
    }
}