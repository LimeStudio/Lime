package com.moi.lime.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.db.LimeDbTest
import org.junit.Rule
import org.junit.Test

class MusicMapperTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()


    @Test
    fun testSaveAll(){
        val musicMapper = MusicMapper(MusicEntityCreator.createRecommendMusicEntity(),MusicEntityCreator.createMusicUrlsEntity())
        musicMapper.saveMusic(db)
        db.musicInformationDao().getAllMusicInformation()
                .test()
                .assertValue {
                    it.size == 30
                }
    }
}