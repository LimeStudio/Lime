package com.moi.lime.ui

import androidx.paging.DataSource
import com.moi.lime.repository.DataBean
import com.moi.lime.repository.ListDataRepository
import javax.inject.Inject

class CustomPageDataSourceFactory @Inject constructor(private val listDataRepository: ListDataRepository)
    :DataSource.Factory<Int,DataBean>(){
    override fun create(): DataSource<Int, DataBean> {
        return CustomPageDataSource(listDataRepository)
    }
}