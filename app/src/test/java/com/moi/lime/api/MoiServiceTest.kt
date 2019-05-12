package com.moi.lime.api

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MoiServiceTest {
    private lateinit var service: MoiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MoiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass
                .getResourceAsStream("/api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream!!))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
                mockResponse
                        .setBody(source.readString(Charsets.UTF_8))
        )
    }

    @Test
    fun getMusicUrl() {
        enqueueResponse("GetMusicUrlResponse")
        service.getMusicUrl("33894312").test().assertValue { it.data!!.first()!!.id.toString() == "33894312" }
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/music/url?id=33894312"))
    }

    //    @Test
//    fun testSignIn() {
//        enqueueResponse("SignInResponse")
//        service.signInByPhone("1234", "1234").test().assertValue {
//            it.clientId == "65672a0a6a69da4901d16ed7c3f25f44b563c999c748a72a0e7ab147cc64ab29c571dfcb70721788e484609ba99b83fb"
//        }
//        val request = mockWebServer.takeRequest()
//        Assert.assertThat(request.path, `is`("/login/cellphone?phone=1234&password=1234"))
//    }
    @Test
    fun testSignIn() = runBlocking {
        enqueueResponse("SignInResponse")
        val signInByPhoneBean = service.signInByPhone("1234", "1234")
        assertEquals("65672a0a6a69da4901d16ed7c3f25f44b563c999c748a72a0e7ab147cc64ab29c571dfcb70721788e484609ba99b83fb", signInByPhoneBean.clientId)
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/login/cellphone?phone=1234&password=1234"))

    }

    @Test
    fun testFetchRecommendSongs() {
        enqueueResponse("RecommendSongsResponse")
        service.fetchRecommendSongs().test().assertValue {
            it.recommend?.size == 30
        }
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/recommend/songs"))
    }

    @Test
    fun testFetchMusicUrls() {
        enqueueResponse("MusicUrlsResponse")
        service.fetchMusicUrlById("123").test().assertValue {
            it.data?.size == 2 && it.data?.first()?.id == 33071205L
        }
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/music/url?id=123"))

    }

}