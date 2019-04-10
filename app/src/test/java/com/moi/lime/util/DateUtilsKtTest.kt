package com.moi.lime.util

import org.junit.Test

import org.junit.Assert.*

class DateUtilsKtTest {

    @Test
    fun testMillisecondToMinute() {
        val duration =180000L
        assertEquals("3:00", millisecondToMinute(duration))
    }
}