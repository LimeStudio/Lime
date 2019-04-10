package com.moi.lime.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.MusicEntityCreator
import com.moi.lime.util.MusicMapper
import com.moi.lime.util.RxSchedulerRule
import com.moi.lime.util.mock
import com.moi.lime.vo.MusicInformation
import com.moi.lime.vo.Resource
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

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
    fun fetchRecommendMusicsWithNetWork() {
        val recommendMusicEntity = MusicEntityCreator.createRecommendMusicEntity()
        `when`(service.fetchMusicUrlById(ArgumentMatchers.anyString())).thenReturn(Flowable.just(MusicEntityCreator.createMusicUrlsEntity()))
        `when`(service.fetchRecommendSongs()).thenReturn(Flowable.just(recommendMusicEntity))
        limeRepository = LimeRepository(userManager, service, db)

        limeRepository.fetchRecommendMusics(false)
        verify(service).fetchRecommendSongs()
        verify(service).fetchMusicUrlById(recommendMusicEntity.recommend.map { it.id }.joinToString(","))
        db.musicInformationDao().getAllMusicInformation()
                .test()
                .assertValue {
                    it.size == 30
                }

    }

    @Test
    fun fetchRecommendMusicsWithDb() {
        val musicMapper = MusicMapper(MusicEntityCreator.createRecommendMusicEntity(), MusicEntityCreator.createMusicUrlsEntity())
        musicMapper.saveMusic(db)
        limeRepository = LimeRepository(userManager, service, db)
        val observer = mock<Observer<Resource<List<MusicInformation>>>>()
        limeRepository.fetchRecommendMusics(true)
                .observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.any())
        verify(service, never()).fetchRecommendSongs()
        verify(service, never()).fetchMusicUrlById(ArgumentMatchers.anyString())

    }
}