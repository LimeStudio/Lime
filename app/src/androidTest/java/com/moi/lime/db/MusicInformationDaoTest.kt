package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.util.TestDispatchersRule
import com.moi.lime.vo.LimeAlbum
import com.moi.lime.vo.LimeArtist
import com.moi.lime.vo.LimeMusic
import com.moi.lime.vo.LimeUrl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

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