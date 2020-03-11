package com.moi.lime.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.db.LimeDbTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class MusicMapperTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()


    @ExperimentalCoroutinesApi
    @Test
    fun testSaveAll()=testDispatchersRule.testScope.runBlockingTest{
        val musicMapper = MusicMapper(MusicEntityCreator.createRecommendMusicEntity(),MusicEntityCreator.createMusicUrlsEntity())
        musicMapper.saveMusic(db)

        val musicInformation = db.musicInformationDao().getAllMusicInformation()
        with(musicInformation) {
            assertEquals(30,size)
        }
    }
}