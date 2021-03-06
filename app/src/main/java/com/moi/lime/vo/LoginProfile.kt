package com.moi.lime.vo

data class LoginProfile(
        val birthday: Long? = null,
        val backgroundUrl: String? = null,
        val detailDescription: String? = null,
        val gender: Int? = null,
        val city: Int? = null,
        val signature: String? = null,
        val description: String? = null,
        val remarkName: Any? = null,
        val accountStatus: Int? = null,
        val avatarImgId: Long? = null,
        val defaultAvatar: Boolean? = null,
        val backgroundImgIdStr: String? = null,
        val province: Int? = null,
        val nickname: String? = null,
        val expertTags: Any? = null,
        val djStatus: Int? = null,
        val avatarUrl: String? = null,
        val authStatus: Int? = null,
        val vipType: Int? = null,
        val userId: Int? = null,
        val followed: Boolean? = null,
        val mutual: Boolean? = null,
        val avatarImgIdStr: String? = null,
        val authority: Int? = null,
        val backgroundImgId: Long? = null,
        val userType: Int? = null,
        val experts: Experts? = null,
        val followeds: Int = 0,
        val follows: Int = 0
)