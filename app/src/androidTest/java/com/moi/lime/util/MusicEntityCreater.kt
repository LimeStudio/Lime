package com.moi.lime.util

import com.moi.lime.vo.MusicUrlsEntity
import com.moi.lime.vo.RecommendSongsEntity
import okio.Okio

object MusicEntityCreater {
    fun createRecommendMusicEntity(): RecommendSongsEntity {
        val inputStream = javaClass
                .getResourceAsStream("/json/RecommendSongsResponse")
        val source = Okio.buffer(Okio.source(inputStream!!))
        return source.readString(Charsets.UTF_8).toBean<RecommendSongsEntity>()!!
    }

    fun createMusicUrlsEntity(): MusicUrlsEntity {
        val inputStream = javaClass
                .getResourceAsStream("/json/MusicUrlsResponse")
        val source = Okio.buffer(Okio.source(inputStream!!))
        return source.readString(Charsets.UTF_8).toBean<MusicUrlsEntity>()!!
    }
}