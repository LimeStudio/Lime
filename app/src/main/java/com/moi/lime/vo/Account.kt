package com.moi.lime.vo

data class Account(
	val salt: String? = null,
	val vipType: Int? = null,
	val userName: String? = null,
	val type: Int? = null,
	val ban: Int? = null,
	val anonimousUser: Boolean? = null,
	val createTime: Long? = null,
	val tokenVersion: Int? = null,
	val id: Int? = null,
	val whitelistAuthority: Int? = null,
	val baoyueVersion: Int? = null,
	val viptypeVersion: Long? = null,
	val donateVersion: Int? = null,
	val status: Int? = null
)
