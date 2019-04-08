package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.vo.LimeAlbum
import com.moi.lime.vo.LimeArtist
import com.moi.lime.vo.LimeMusic
import com.moi.lime.vo.LimeUrl
import org.junit.Rule
import org.junit.Test

class MusicInformationDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testLoadAll() {
        createAndInsertData()
        db.musicInformationDao()
                .getAllMusicInformation()
                .test()
                .assertValue {
                    it.size == 1 &&
                            it.first().limeMusic?.id == "1" &&
                            it.first().limeUrls.size == 2 &&
                            it.first().limeAlbum.size == 1 &&
                            it.first().limeArtist.size == 1
                }

    }


    private fun createAndInsertData() {
        val limeMusic = LimeMusic("test1", "1", 100, "test1")
        val limeUrl = LimeUrl("1", "400", "400", "test1")
        val limeUrl2 = LimeUrl("1", "400", "400", "test1")
        val limeArtist = LimeArtist("text","1", "2", "test1", "test1")
        val limeAlbum = LimeAlbum("1", "test", "1", "test", 100, "1", "test", "test", "test")
        db.limeMusicDao().insertAll(limeMusic)
        db.limeUrlDao().insertAll(limeUrl, limeUrl2)
        db.limeArtistDao().insertAll(limeArtist)
        db.limeAlbumDao().insertAll(limeAlbum)

    }

}