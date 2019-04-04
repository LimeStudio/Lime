package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class MusicUrlsEntity(@SerializedName("code")
                           val code: Int = 0,
                           @SerializedName("data")
                           val data: List<DataItem>?)