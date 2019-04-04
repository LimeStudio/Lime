package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class Album(@SerializedName("transName")
                 val transName: String? = null,
                 @SerializedName("publishTime")
                 val publishTime: Long = 0,
                 @SerializedName("artist")
                 val artist: Artist,
                 @SerializedName("blurPicUrl")
                 val blurPicUrl: String = "",
                 @SerializedName("description")
                 val description: String = "",
                 @SerializedName("commentThreadId")
                 val commentThreadId: String = "",
                 @SerializedName("pic")
                 val pic: Long = 0,
                 @SerializedName("type")
                 val type: String = "",
                 @SerializedName("tags")
                 val tags: String = "",
                 @SerializedName("picUrl")
                 val picUrl: String = "",
                 @SerializedName("companyId")
                 val companyId: Int = 0,
                 @SerializedName("size")
                 val size: Int = 0,
                 @SerializedName("briefDesc")
                 val briefDesc: String = "",
                 @SerializedName("copyrightId")
                 val copyrightId: Int = 0,
                 @SerializedName("artists")
                 val artists: List<ArtistsItem>?,
                 @SerializedName("name")
                 val name: String = "",
                 @SerializedName("company")
                 val company: String = "",
                 @SerializedName("subType")
                 val subType: String = "",
                 @SerializedName("id")
                 val id: Int = 0,
                 @SerializedName("picId")
                 val picId: Long = 0,
                 @SerializedName("status")
                 val status: Int = 0)