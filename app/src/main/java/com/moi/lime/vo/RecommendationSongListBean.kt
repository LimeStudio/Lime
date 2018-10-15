package com.moi.lime.vo

data class RecommendationSongListBean(
	val haveRcmdSongs: Boolean? = null,
	val code: Int? = null,
	val featureFirst: Boolean? = null,
	val recommend: List<RecommendItem?>? = null
)
