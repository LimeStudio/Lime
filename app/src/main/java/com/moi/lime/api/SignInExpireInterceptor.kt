package com.moi.lime.api

import com.moi.lime.core.rxjava.RxBus
import com.moi.lime.util.toBean
import com.moi.lime.vo.OnlyCodeBean
import com.moi.lime.vo.SignInExpireEvent
import okhttp3.Interceptor
import okhttp3.Response

class SignInExpireInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val content = response.body()?.string()
        val codeBean = content?.toBean<OnlyCodeBean>() ?: return response
        if (codeBean.code==301){
            RxBus.INSTANCE.post(SignInExpireEvent())
        }
        return response
    }

}