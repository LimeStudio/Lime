package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class PlaylistItem(@SerializedName("privacy")
                        val privacy: Long?,
                        @SerializedName("description")
                        val description: String?,
                        @SerializedName("trackNumberUpdateTime")
                        val trackNumberUpdateTime: Long?,
                        @SerializedName("subscribed")
                        val subscribed: Boolean? = false,
                        @SerializedName("trackCount")
                        val trackCount: Long?,
                        @SerializedName("adType")
                        val adType: Long?,
                        @SerializedName("coverImgId_str")
                        val coverImgIdStr: String?,
                        @SerializedName("specialType")
                        val specialType: Long?,
                        @SerializedName("artists")
                        val artists: String?,
                        @SerializedName("id")
                        val id: Long?,
                        @SerializedName("totalDuration")
                        val totalDuration: Long?,
                        @SerializedName("ordered")
                        val ordered: Boolean? = false,
                        @SerializedName("creator")
                        val creator: Creator?,
                        @SerializedName("highQuality")
                        val highQuality: Boolean? = false,
                        @SerializedName("commentThreadId")
                        val commentThreadId: String?,
                        @SerializedName("updateTime")
                        val updateTime: Long?,
                        @SerializedName("trackUpdateTime")
                        val trackUpdateTime: Long?,
                        @SerializedName("userId")
                        val userId: Long?,
                        @SerializedName("tracks")
                        val tracks: String?,
                        @SerializedName("tags")
                        val tags: List<String>?,
                        @SerializedName("anonimous")
                        val anonimous: Boolean? = false,
                        @SerializedName("coverImgUrl")
                        val coverImgUrl: String?,
                        @SerializedName("cloudTrackCount")
                        val cloudTrackCount: Long?,
                        @SerializedName("playCount")
                        val playCount: Long?,
                        @SerializedName("coverImgId")
                        val coverImgId: Long?,
                        @SerializedName("createTime")
                        val createTime: Long?,
                        @SerializedName("name")
                        val name: String?,
                        @SerializedName("subscribedCount")
                        val subscribedCount: Long?,
                        @SerializedName("status")
                        val status: Long?,
                        @SerializedName("newImported")
                        val newImported: Boolean? = false)