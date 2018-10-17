package com.moi.lime.util

fun <T> T.toJson(): String {
    val gson = GsonTools.gson
    return gson.toJson(this)
}

inline fun <reified T> String.toBean(): T {
    val gson = GsonTools.gson
    return gson.fromJson(this, T::class.java)
}