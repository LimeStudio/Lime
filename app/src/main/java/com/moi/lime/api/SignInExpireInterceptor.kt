package com.moi.lime.api

import com.moi.lime.util.toBean
import com.moi.lime.vo.OnlyCodeBean
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.lang.Exception


class SignInExpireInterceptor : Interceptor {
    var onLoginExpire: () -> Unit = {}
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val content = response.body()?.string() ?: ""
        val mediaType = response.body()?.contentType()
        val returnResponse =
                response.newBuilder()
                        .body(ResponseBody.create(mediaType, content))
                        .build()

        try {
            val codeBean = content.toBean<OnlyCodeBean>() ?: return returnResponse
            if (codeBean.code == 301) {
                onLoginExpire()
            }
        } catch (e: Exception) {
            return returnResponse
        }

        return returnResponse
    }

}