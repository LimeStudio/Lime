package com.moi.lime.vo

data class Resource<out T>(val status: Status, val data: T?, val throwable: Throwable?) {
    companion object {
        fun <T> success(data:T?):Resource<T>{
            return Resource(Status.SUCCESS,data,null)
        }

        fun <T> error(throwable: Throwable?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, throwable)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}