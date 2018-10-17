package com.moi.lime.vo

import androidx.room.Entity

@Entity(primaryKeys = ["uid"])
data class Profile(
        val isSignIn:Boolean,
        val uid: String,
        val createTime: Long,
        val vipType: Int,
        val nickName :String,
        val gender:String,
        val birthday:Long,
        val province:String,
        val defaultAvatar:Boolean,
        val avatarUrl :String,
        val backgroundUrl: String
)