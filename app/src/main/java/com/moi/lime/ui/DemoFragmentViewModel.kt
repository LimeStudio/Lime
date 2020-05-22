package com.moi.lime.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.moi.lime.core.livedata.SingleLiveEvent
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.AbsentLiveData
import com.moi.lime.vo.Resource
import javax.inject.Inject

class DemoFragmentViewModel @Inject constructor(limeRepository: LimeRepository) : ViewModel() {

    //获取全部商品
    private val getProductsEvent = SingleLiveEvent<Unit>()
    val products = getProductsEvent.switchMap {
        limeRepository.getProducts()
    }


    //添加一个商品
    val addProductEvent = SingleLiveEvent<String>()
    val addProductResult = addProductEvent.switchMap {
        limeRepository.addProducts(it)
    }

    //添加一个商品到购物车
    val addProductToCartEvent = SingleLiveEvent<String>()
    val addProductToCartResult = addProductToCartEvent.switchMap {
        val name = products.value?.data?.random()?.name //随机添加一个到购物车
        if (name !== null) {
            limeRepository.addProductsToCart(name)

        } else {
            AbsentLiveData.create() //为空返回一个发送null的livedata
        }

    }


    //把列表转换成一个string 展示
    val displayProducts = products.map {
        it.data?.fold("") { s, product ->
            "$s\n${product.name}"
        }

    }

    //购物车的展示
    val productsInCart = products.map {
        it.data?.filter { product ->
                    product.selectedCount > 0
                }
                ?.fold("") { s, product ->
                    "$s\n${product.name}X${product.selectedCount}"
                }
    }


    init {
        getProductsEvent.value = Unit

        //全商品数据，去监听添加商品的接口，等成功的时候更新自己
        (products as MediatorLiveData).addSource(addProductResult) {
            it.data?.let { product ->
                val list = products.value?.data ?: listOf()
                products.value = Resource.success(list.toMutableList().apply {
                    add(product)
                })
            }
        }

        //全商品数据，去监听添加购物车接口，等成功后更新自己
        products.addSource(addProductToCartResult) {
            val list = products.value?.data ?: listOf()
            list.firstOrNull { product ->
                product.name == it.data?.name
            }?.let {
                it.selectedCount++
            }
            products.value = products.value
        }

    }


}