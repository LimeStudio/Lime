package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.util.TestDispatchersRule
import com.moi.lime.util.observeForTesting
import com.moi.lime.vo.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class MusicInformationDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()

    @ExperimentalCoroutinesApi
    @Test
    fun testLoadAll() = testDispatchersRule.testScope.runBlockingTest {
        createAndInsertData()
        val musicInformation = db.musicInformationDao().getAllMusicInformation()
        with(musicInformation) {
            assertEquals(1, size)
            assertEquals("1", first().limeMusic?.id ?: "-1")
            assertEquals(2, first().limeUrls.size)
            assertEquals(1, first().limeAlbum.size)
            assertEquals(1, first().limeArtist.size)

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testFindById() = testDispatchersRule.testScope.runBlockingTest {
        createAndInsertData()
        val musicInformation = db.musicInformationDao().getMusicInformationFromMusicId("1")
        assertNotNull(musicInformation)
        with(musicInformation) {
            assertEquals("1", limeMusic?.id ?: "-1")
            assertEquals(2, limeUrls.size)
            assertEquals(1, limeAlbum.size)
            assertEquals(1, limeArtist.size)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testFindByIdWithNull() = testDispatchersRule.testScope.runBlockingTest {
        val musicInformation = db.musicInformationDao().getMusicInformationFromMusicId("2")
        assertNull(musicInformation)

    }

    @Test
    fun testGetAllMusicInformationLiveData() {
        createAndInsertData()
        val liveData = db.musicInformationDao().getAllMusicInformationLiveData()
        liveData.observeForTesting {
            assertEquals(1, liveData.value?.size)
        }

    }

    @Test
    fun testGetAllMusicInformationLiveDataWithNull() {
        val liveData = db.musicInformationDao().getAllMusicInformationLiveData()
        liveData.observeForTesting {
            assertEquals(0, liveData.value?.size)
        }

    }

    private fun createAndInsertData() {
        val limeMusic = LimeMusic("test1", "1", 100, "test1")
        val limeUrl = LimeUrl("url", "1", "400", "400", "test1")
        val limeUrl2 = LimeUrl("url", "1", "400", "400", "test1")
        val limeArtist = LimeArtist("text", "1", "2", "test1", "test1")
        val limeAlbum = LimeAlbum("1", "test", "1", "test", 100, "1", "test", "test", "test")
        db.limeMusicDao().insertAll(limeMusic)
        db.limeUrlDao().insertAll(limeUrl, limeUrl2)
        db.limeArtistDao().insertAll(limeArtist)
        db.limeAlbumDao().insertAll(limeAlbum)

    }

}