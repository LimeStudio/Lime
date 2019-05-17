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
    fun getMusicUrl() = runBlocking {
        enqueueResponse("GetMusicUrlResponse")
        val musicUrlBean = service.getMusicUrl("33894312")
        assertEquals("33894312", musicUrlBean.data?.first()?.id.toString())
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/music/url?id=33894312"))
    }

    @Test
    fun testSignIn() = runBlocking {
        enqueueResponse("SignInResponse")
        val signInByPhoneBean = service.signInByPhone("1234", "1234")
        assertEquals("65672a0a6a69da4901d16ed7c3f25f44b563c999c748a72a0e7ab147cc64ab29c571dfcb70721788e484609ba99b83fb", signInByPhoneBean.clientId)
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/login/cellphone?phone=1234&password=1234"))

    }

    @Test
    fun testFetchRecommendSongs() = runBlocking {
        enqueueResponse("RecommendSongsResponse")
        val recommend = service.fetchRecommendSongs()
        assertEquals(30, recommend.recommend.size)
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/recommend/songs"))
    }

    @Test
    fun testFetchMusicUrls() = runBlocking {
        enqueueResponse("MusicUrlsResponse")
        val musicUrlsEntity = service.fetchMusicUrlById("123")
        assertEquals(2, musicUrlsEntity.data.size)
        assertEquals(33071205L, musicUrlsEntity.data.first().id)
        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/music/url?id=123"))

    }

}