package com.moi.lime.api

import com.moi.lime.vo.MusicUrlBean
import com.moi.lime.vo.RecommendationSongListBean
import com.moi.lime.vo.SigninByPhoneBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface MoiService {
    @GET("/music/url")
    fun getMusicUrl(@Query("id") id: String): Flowable<MusicUrlBean>

    @GET("/login/cellphone")
    fun signinByPhone(@Query("phone") phoneNumber: String,
                      @Query("password") password: String)
            : Flowable<SigninByPhoneBean>

    @GET("/recommend/resource")
    fun getRecommendationList(): Flowable<RecommendationSongListBean>
}