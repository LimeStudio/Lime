package com.moi.lime.api

import com.moi.lime.vo.MusicUrlBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface MoiService {
    @GET("/music/url")
    fun getMusicUrl(@Query("id")id:String):Flowable<MusicUrlBean>
}