package com.moi.lime.vo

data class SigninByPhoneBean(
	val clientId: String? = null,
	val code: Int? = null,
	val loginType: Int? = null,
	val profile: LoginProfile? = null,
	val bindings: List<BindingsItem?>? = null,
	val effectTime: Int? = null,
	val account: Account? = null
)
