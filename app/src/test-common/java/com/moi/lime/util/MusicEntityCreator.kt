package com.moi.lime.util

import com.moi.lime.vo.MusicUrlsEntity
import com.moi.lime.vo.RecommendSongsEntity
import okio.Okio

object MusicEntityCreator {
    fun createRecommendMusicEntity(): RecommendSongsEntity {
        return loadJsonFromFilePath("/json/RecommendSongsResponse", javaClass).toBean<RecommendSongsEntity>()!!
    }

    fun createMusicUrlsEntity(): MusicUrlsEntity {
        return loadJsonFromFilePath("/json/MusicUrlsResponse", javaClass).toBean<MusicUrlsEntity>()!!
    }
}