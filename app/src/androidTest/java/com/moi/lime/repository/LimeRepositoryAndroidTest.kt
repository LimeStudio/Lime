package com.moi.lime.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.*
import com.moi.lime.vo.MusicInformation
import com.moi.lime.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class LimeRepositoryAndroidTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()

    private lateinit var limeRepository: LimeRepository
    private val service = mock<MoiService>()
    private val userManager = mock<UserManager>()

    @ExperimentalCoroutinesApi
    @Test
    fun fetchRecommendMusicsWithNetWork() = testDispatchersRule.testScope.runBlockingTest {
        val recommendMusicEntity = MusicEntityCreator.createRecommendMusicEntity()
        `when`(service.fetchMusicUrlById(ArgumentMatchers.anyString())).thenReturn(MusicEntityCreator.createMusicUrlsEntity())
        `when`(service.fetchRecommendSongs()).thenReturn(recommendMusicEntity)
        limeRepository = LimeRepository(userManager, service, db, TestDispatchers(testDispatchersRule.testDispatcher))
        limeRepository.fetchRecommendMusics(false).observeForever { Unit }
        verify(service).fetchRecommendSongs()
        verify(service).fetchMusicUrlById(recommendMusicEntity.recommend.map { it.id }.joinToString(","))
        val musicInformation = db.musicInformationDao().getAllMusicInformation()
        with(musicInformation) {
            assertEquals(30, size)
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchRecommendMusicsWithDb() = testDispatchersRule.testScope.runBlockingTest {
        val musicMapper = MusicMapper(MusicEntityCreator.createRecommendMusicEntity(), MusicEntityCreator.createMusicUrlsEntity())
        musicMapper.saveMusic(db)
        limeRepository = LimeRepository(userManager, service, db, TestDispatchers(testDispatchersRule.testDispatcher))
        val observer = mock<Observer<Resource<List<MusicInformation>>>>()
        limeRepository.fetchRecommendMusics(true)
                .observeForever(observer)
        verify(observer, times(2)).onChanged(ArgumentMatchers.any())
        verify(service, never()).fetchRecommendSongs()
        verify(service, never()).fetchMusicUrlById(ArgumentMatchers.anyString())

    }
}