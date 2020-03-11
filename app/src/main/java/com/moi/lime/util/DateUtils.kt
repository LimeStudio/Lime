package com.moi.lime.util

fun millisecondToMinute(millisecond: Long): String {
    val minute = ((millisecond / 1000) / 60).toInt()
    val second = ((millisecond / 1000) % 60).toInt()
    val s = if (second < 10) {
        "0$second"
    } else {
        second.toString()
    }
    return "$minute:$s"
}