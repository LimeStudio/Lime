package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class Creator(@SerializedName("birthday")
                   val birthday: Long?,
                   @SerializedName("detailDescription")
                   val detailDescription: String?,
                   @SerializedName("backgroundUrl")
                   val backgroundUrl: String?,
                   @SerializedName("gender")
                   val gender: Long?,
                   @SerializedName("city")
                   val city: Long?,
                   @SerializedName("signature")
                   val signature: String?,
                   @SerializedName("description")
                   val description: String?,
                   @SerializedName("remarkName")
                   val remarkName: String?,
                   @SerializedName("accountStatus")
                   val accountStatus: Long?,
                   @SerializedName("avatarImgId")
                   val avatarImgId: Long?,
                   @SerializedName("defaultAvatar")
                   val defaultAvatar: Boolean? = false,
                   @SerializedName("avatarImgIdStr")
                   val avatarImgIdStr: String?,
                   @SerializedName("backgroundImgIdStr")
                   val backgroundImgIdStr: String?,
                   @SerializedName("province")
                   val province: Long?,
                   @SerializedName("nickname")
                   val nickname: String?,
                   @SerializedName("expertTags")
                   val expertTags: List<String>?,
                   @SerializedName("djStatus")
                   val djStatus: Long?,
                   @SerializedName("avatarUrl")
                   val avatarUrl: String?,
                   @SerializedName("authStatus")
                   val authStatus: Long?,
                   @SerializedName("vipType")
                   val vipType: Long?,
                   @SerializedName("followed")
                   val followed: Boolean? = false,
                   @SerializedName("userId")
                   val userId: Long?,
                   @SerializedName("mutual")
                   val mutual: Boolean? = false,
                   @SerializedName("authority")
                   val authority: Long?,
                   @SerializedName("userType")
                   val userType: Long?,
                   @SerializedName("backgroundImgId")
                   val backgroundImgId: Long?)