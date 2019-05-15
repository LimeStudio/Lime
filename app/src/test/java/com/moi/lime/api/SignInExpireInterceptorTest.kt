package com.moi.lime.api

import com.moi.lime.util.mock
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInExpireInterceptorTest {


    private lateinit var service: MoiService

    private lateinit var mockWebServer: MockWebServer

    private val signInExpireInterceptor = SignInExpireInterceptor()

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        val client = OkHttpClient.Builder()
                .addInterceptor(signInExpireInterceptor)
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun testInterceptor() = runBlocking {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(
                mockResponse
                        .setBody("{\n" +
                                "    \"msg\": null,\n" +
                                "    \"code\": 301\n" +
                                "}")
        )

        val callback: () -> Unit = mock()
        signInExpireInterceptor.onLoginExpire = callback
        service.getMusicUrl("test")
        Mockito.verify(callback).invoke()

    }
}