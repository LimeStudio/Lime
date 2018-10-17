package com.moi.lime.vo

data class BindingsItem(
	val expiresIn: Int? = null,
	val expired: Boolean? = null,
	val tokenJsonStr: String? = null,
	val refreshTime: Int? = null,
	val id: Long? = null,
	val type: Int? = null,
	val userId: Int? = null,
	val url: String? = null
)
