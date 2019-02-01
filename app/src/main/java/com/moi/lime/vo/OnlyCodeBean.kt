package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class OnlyCodeBean(@SerializedName("code")
                        val code: Int = 0)