package com.moi.lime.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
        @PrimaryKey val isSignIn: Boolean = true,
        val uid: String,
        val createTime: Long,
        val vipType: Int,
        val nickName: String,
        val gender: String,
        val birthday: Long,
        val province: String,
        val defaultAvatar: Boolean,
        val avatarUrl: String,
        val backgroundUrl: String
)