package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class RecommendSongsEntity(@SerializedName("code")
                                val code: Int = 0,
                                @SerializedName("recommend")
                                val recommend: List<RecommendItem>?)