package com.moi.lime.api

import com.moi.lime.core.rxjava.RxBus
import com.moi.lime.util.RxSchedulerRule
import com.moi.lime.vo.SignInExpireEvent
import io.reactivex.subscribers.TestSubscriber
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SignInExpireInterceptorTest {
    @Rule
    @JvmField
    val rxRule = RxSchedulerRule()

    private lateinit var service: MoiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        val client = OkHttpClient.Builder()
                .addInterceptor(SignInExpireInterceptor())
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MoiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun testInterceptor() {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(
                mockResponse
                        .setBody("{\n" +
                                "    \"msg\": null,\n" +
                                "    \"code\": 301\n" +
                                "}")
        )
        val testSubscriber = TestSubscriber<SignInExpireEvent>()
        RxBus.INSTANCE.toFlowable<SignInExpireEvent>()
                .subscribe (testSubscriber)
        service.getMusicUrl("test").subscribe()
        testSubscriber.assertValue{ true }


    }
}