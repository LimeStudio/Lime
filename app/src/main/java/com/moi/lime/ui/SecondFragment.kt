package com.moi.lime.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.moi.lime.R
import com.moi.lime.di.Injectable
import com.moi.lime.util.Logger
import kotlinx.android.synthetic.main.fragment_second.*
import javax.inject.Inject

class SecondFragment : Fragment(),Injectable {
    @Inject lateinit var customPageDataSourceFactory: CustomPageDataSourceFactory
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?): Unit = view!!.let { view ->
        super.onActivityCreated(savedInstanceState)
        val args = SecondFragmentArgs.fromBundle(arguments)
        val adapter = CustomAdapter()
        list.layoutManager=LinearLayoutManager(list.context)
        list.adapter = adapter
         val data = LivePagedListBuilder(customPageDataSourceFactory,PagedList.Config.Builder()
                 .setPageSize(20)
                 .setEnablePlaceholders(true)
                 .setInitialLoadSizeHint(20)
                 .build()).build()

        data.observe(this, Observer {
            adapter.submitList(it)
        })

    }
}