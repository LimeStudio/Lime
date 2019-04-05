package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class RecommendItem(@SerializedName("no")
                         val no: Int = 0,
                         @SerializedName("reason")
                         val reason: String = "",
                         @SerializedName("copyright")
                         val copyright: Int = 0,
                         @SerializedName("dayPlays")
                         val dayPlays: Int = 0,
                         @SerializedName("fee")
                         val fee: Int = 0,
                         @SerializedName("sign")
                         val sign: String? = null,
                         @SerializedName("rurl")
                         val rurl: String? = null,
                         @SerializedName("privilege")
                         val privilege: Privilege,
                         @SerializedName("mMusic")
                         val mMusic: Music,
                         @SerializedName("bMusic")
                         val music: Music,
                         @SerializedName("duration")
                         val duration: Long = 0,
                         @SerializedName("score")
                         val score: Int = 0,
                         @SerializedName("rtype")
                         val rtype: Int = 0,
                         @SerializedName("starred")
                         val starred: Boolean = false,
                         @SerializedName("artists")
                         val artists: List<ArtistsItem>,
                         @SerializedName("popularity")
                         val popularity: Int = 0,
                         @SerializedName("playedNum")
                         val playedNum: Int = 0,
                         @SerializedName("hearTime")
                         val hearTime: Int = 0,
                         @SerializedName("starredNum")
                         val starredNum: Int = 0,
                         @SerializedName("id")
                         val id: Long = 0,
                         @SerializedName("mp3Url")
                         val mpUrl: String? = null,
                         @SerializedName("alg")
                         val alg: String = "",
                         @SerializedName("audition")
                         val audition: String? = null,
                         @SerializedName("transName")
                         val transName: String? = null,
                         @SerializedName("album")
                         val album: Album,
                         @SerializedName("lMusic")
                         val lMusic: Music,
                         @SerializedName("ringtone")
                         val ringtone: String = "",
                         @SerializedName("commentThreadId")
                         val commentThreadId: String = "",
                         @SerializedName("copyFrom")
                         val copyFrom: String = "",
                         @SerializedName("crbt")
                         val crbt: String? = null,
                         @SerializedName("rtUrl")
                         val rtUrl: String? = null,
                         @SerializedName("ftype")
                         val ftype: Int = 0,
                         @SerializedName("copyrightId")
                         val copyrightId: Int = 0,
                         @SerializedName("hMusic")
                         val hMusic: Music,
                         @SerializedName("mvid")
                         val mvid: Int = 0,
                         @SerializedName("name")
                         val name: String = "",
                         @SerializedName("disc")
                         val disc: String = "",
                         @SerializedName("position")
                         val position: Int = 0,
                         @SerializedName("status")
                         val status: Int = 0)