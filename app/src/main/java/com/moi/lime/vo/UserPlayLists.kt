package com.moi.lime.vo

import com.google.gson.annotations.SerializedName

data class UserPlayLists(@SerializedName("code")
                         val code: Int = 0,
                         @SerializedName("playlist")
                         var playlist: List<PlaylistItem>?,
                         @SerializedName("more")
                         val more: Boolean = false)