package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class MusicUrlEntity(@SerializedName("code")
                          val code: Int = 0,
                          @SerializedName("expi")
                          val expi: Int = 0,
                          @SerializedName("flag")
                          val flag: Int = 0,
                          @SerializedName("level")
                          val level: String = "",
                          @SerializedName("fee")
                          val fee: Int = 0,
                          @SerializedName("type")
                          val type: String = "",
                          @SerializedName("canExtend")
                          val canExtend: Boolean = false,
                          @SerializedName("url")
                          val url: String = "",
                          @SerializedName("gain")
                          val gain: Double = 0.0,
                          @SerializedName("br")
                          val br: Int = 0,
                          @SerializedName("uf")
                          val uf: String? = null,
                          @SerializedName("size")
                          val size: Int = 0,
                          @SerializedName("encodeType")
                          val encodeType: String = "",
                          @SerializedName("id")
                          val id: Int = 0,
                          @SerializedName("md5")
                          val md: String = "",
                          @SerializedName("payed")
                          val payed: Int = 0,
                          @SerializedName("freeTrialInfo")
                          val freeTrialInfo: String? = null)