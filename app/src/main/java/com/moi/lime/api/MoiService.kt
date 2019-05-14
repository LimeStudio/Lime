package com.moi.lime.api

import com.moi.lime.vo.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface MoiService {
    @GET("music/url")
    fun getMusicUrl(@Query("id") id: String): Flowable<MusicUrlBean>

    @GET("login/cellphone")
    suspend fun signInByPhone(@Query("phone") phoneNumber: String,
                      @Query("password") password: String)
            : SignInByPhoneBean

    @GET("login/refresh")
    fun signInRefresh(): Flowable<OnlyCodeBean>

    @GET("recommend/songs")
    suspend fun fetchRecommendSongs(): RecommendSongsEntity

    @GET("music/url")
    suspend fun fetchMusicUrlById(@Query("id") ids: String): MusicUrlsEntity

}