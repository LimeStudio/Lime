package com.moi.lime.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.RxSchedulerRule
import com.moi.lime.util.mock
import com.moi.lime.util.toBean
import com.moi.lime.vo.MusicUrlsEntity
import com.moi.lime.vo.RecommendSongsEntity
import io.reactivex.Flowable
import okio.Okio
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class LimeRepositoryAndroidTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var limeRepository: LimeRepository
    private val service = mock<MoiService>()
    private val userManager = mock<UserManager>()
    @Test
    fun fetchRecommendMusics() {
        val recommendMusicEntity = createRecommendMusicEntity()
        `when`(service.fetchMusicUrlById(ArgumentMatchers.anyString())).thenReturn(Flowable.just(createMusicUrlsEntity()))
        `when`(service.fetchRecommendSongs()).thenReturn(Flowable.just(recommendMusicEntity))
        limeRepository = LimeRepository(userManager, service, db)

        limeRepository.fetchRecommendMusics(true)
        verify(service).fetchRecommendSongs()
        verify(service).fetchMusicUrlById(recommendMusicEntity.recommend.map { it.id }.joinToString(","))
        db.musicInformationDao().getAllMusicInformation()
                .test()
                .assertValue {
                    it.size == 30
                }
    }

    private fun createRecommendMusicEntity(): RecommendSongsEntity {
        val inputStream = javaClass
                .getResourceAsStream("/json/RecommendSongsResponse")
        val source = Okio.buffer(Okio.source(inputStream!!))
        return source.readString(Charsets.UTF_8).toBean<RecommendSongsEntity>()!!
    }

    private fun createMusicUrlsEntity(): MusicUrlsEntity {
        val inputStream = javaClass
                .getResourceAsStream("/json/MusicUrlsResponse")
        val source = Okio.buffer(Okio.source(inputStream!!))
        return source.readString(Charsets.UTF_8).toBean<MusicUrlsEntity>()!!
    }
}