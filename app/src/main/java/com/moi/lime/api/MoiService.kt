package com.moi.lime.api

import com.moi.lime.vo.*
import retrofit2.http.GET
import retrofit2.http.Query


interface MoiService {
    @GET("music/url")
    suspend fun getMusicUrl(@Query("id") id: String): MusicUrlBean

    @GET("login/cellphone")
    suspend fun signInByPhone(@Query("phone") phoneNumber: String,
                      @Query("password") password: String)
            : SignInByPhoneBean

    @GET("login/refresh")
    suspend fun signInRefresh(): OnlyCodeBean

    @GET("recommend/songs")
    suspend fun fetchRecommendSongs(): RecommendSongsEntity

    @GET("music/url")
    suspend fun fetchMusicUrlById(@Query("id") ids: String): MusicUrlsEntity

}