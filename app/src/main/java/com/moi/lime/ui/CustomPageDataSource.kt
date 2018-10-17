package com.moi.lime.ui

import androidx.paging.PageKeyedDataSource
import com.moi.lime.repository.DataBean
import com.moi.lime.repository.ListDataRepository

class CustomPageDataSource(private val listDataRepository: ListDataRepository)
    : PageKeyedDataSource<Int, DataBean>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DataBean>) {
        val data = listDataRepository.loadData(params.requestedLoadSize)
        callback.onResult(data, null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataBean>) {
        val data = listDataRepository.loadPageData(params.key, params.requestedLoadSize)
        data?.let {
            callback.onResult(it, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataBean>) {
        val data = listDataRepository.loadPageData(params.key, params.requestedLoadSize)
        data?.let {
            callback.onResult(it, params.key - 1)
        }
    }
}