package com.moi.lime.api

import com.moi.lime.vo.MusicUrlBean
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
        val source = Okio.buffer(Okio.source(inputStream))
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
        var musicUrlBean: MusicUrlBean? = null
        service.getMusicUrl("33894312").subscribe { musicUrlBean = it }

        val request = mockWebServer.takeRequest()
        Assert.assertThat(request.path, `is`("/music/url?id=33894312"))
        Assert.assertThat<MusicUrlBean>(musicUrlBean, notNullValue())
        Assert.assertThat(musicUrlBean!!.data!!.first()!!.id.toString(), `is`("33894312"))
    }
}