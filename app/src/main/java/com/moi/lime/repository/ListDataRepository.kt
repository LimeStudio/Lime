package com.moi.lime.repository

import javax.inject.Inject

data class DataBean(val id: Int, val name: String)

class ListDataRepository @Inject constructor() {

    private val data by lazy {
        generateSequence(0) { it + 1 }
                .takeWhile { it <= 100 }
                .map { DataBean(it, "name$it") }
                .toList()
    }

    fun loadData(size: Int): List<DataBean> {
        return data.subList(0, size)
    }

    fun loadData(index: Int, size: Int): List<DataBean>? {
        if (index >= data.size - 1 || index < 1) {
            return null
        }
        if (index + size > data.size) {
            return data.subList(index + 1, data.size)
        }
        return data.subList(index + 1, index + size)
    }

    fun loadPageData(page: Int, size: Int): List<DataBean>? {
        val totalPage = if (data.size % size == 0) {
            data.size / size
        } else {
            data.size / size + 1
        }

        if (page > totalPage || page < 1) {
            return null
        }

        if (page == totalPage) {
            return data.subList((page - 1) * size, data.size)
        }
        return data.subList((page - 1) * size, page * size)
    }
}