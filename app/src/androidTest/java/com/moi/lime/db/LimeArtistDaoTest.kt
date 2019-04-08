package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.vo.LimeArtist
import com.moi.lime.vo.LimeMusic
import org.junit.Rule
import org.junit.Test


class LimeArtistDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadAllTest() {
        val list = createLimeArtistList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeArtistDao().insertAll(*list.toTypedArray())
        db.limeArtistDao().getAll()
                .test()
                .assertValue {
                    it == list && it.size == 2
                }

    }

    @Test
    fun insertAndDeleteAllTest() {
        val list = createLimeArtistList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeArtistDao().insertAll(*list.toTypedArray())
        db.limeArtistDao().deleteAll()
        db.limeArtistDao()
                .getAll()
                .test()
                .assertValue {
                    it.isEmpty()
                }

    }

    private fun createLimeArtistList(): List<LimeArtist> {
        val limeArtist1 = LimeArtist("text", "1", "2", "test1", "test1")
        val limeArtist2 = LimeArtist("text", "1", "3", "test2", "test2")
        return listOf(limeArtist1, limeArtist2)
    }

}