package com.moi.lime.util

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GsonToolsTest {
    @Test
    fun getGsonTest() {
        val gson1 = GsonTools.gson
        val gson2 = GsonTools.gson
        assertEquals(gson1, gson2)
    }
}