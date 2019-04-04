package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class Music(@SerializedName("extension")
                  val extension: String = "",
                 @SerializedName("size")
                  val size: Int = 0,
                 @SerializedName("volumeDelta")
                  val volumeDelta: Float = 0f,
                 @SerializedName("name")
                  val name: String? = null,
                 @SerializedName("bitrate")
                  val bitrate: Int = 0,
                 @SerializedName("playTime")
                  val playTime: Int = 0,
                 @SerializedName("id")
                  val id: String = "",
                 @SerializedName("dfsId")
                  val dfsId: Int = 0,
                 @SerializedName("sr")
                  val sr: Int = 0)