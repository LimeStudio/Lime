package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class Artist(@SerializedName("picUrl")
                  val picUrl: String? = "",
                  @SerializedName("img1v1Url")
                  val imgVUrl: String? = "",
                  @SerializedName("briefDesc")
                  val briefDesc: String? = "",
                  @SerializedName("musicSize")
                  val musicSize: Int = 0,
                  @SerializedName("name")
                  val name: String? = "",
                  @SerializedName("img1v1Id")
                  val imgVId: Int = 0,
                  @SerializedName("id")
                  val id: Int = 0,
                  @SerializedName("picId")
                  val picId: Int = 0,
                  @SerializedName("albumSize")
                  val albumSize: Int = 0,
                  @SerializedName("trans")
                  val trans: String? = "")