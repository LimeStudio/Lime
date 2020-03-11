package com.moi.lime.util

import com.moi.lime.vo.Profile

fun createProfile(isSignIn: Boolean = true): Profile {
    return Profile(
            isSignIn,
            "123456",
            1234556,
            1,
            "test",
            "1",
            123456,
            "1",
            "1",
            true,
            "1",
            "1"
            , 1,
            1)
}

fun <T> loadJsonFromFilePath(path: String, javaClass: Class<T>): String {
    val inputStream = javaClass
            .getResourceAsStream(path)
    return inputStream!!.bufferedReader().use { it.readText() }

}
