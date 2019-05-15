package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.vo.LimeArtist
import com.moi.lime.vo.LimeMusic
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class LimeArtistDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadAllTest() = runBlocking {
        val list = createLimeArtistList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeArtistDao().insertAll(*list.toTypedArray())
        val artists = db.limeArtistDao().getAll()
        assertEquals(2, artists.size)

    }

    @Test
    fun insertAndDeleteAllTest() = runBlocking {
        val list = createLimeArtistList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeArtistDao().insertAll(*list.toTypedArray())
        db.limeArtistDao().deleteAll()
        val artists = db.limeArtistDao().getAll()
        assertEquals(0, artists.size)

    }

    private fun createLimeArtistList(): List<LimeArtist> {
        val limeArtist1 = LimeArtist("text", "1", "2", "test1", "test1")
        val limeArtist2 = LimeArtist("text", "1", "3", "test2", "test2")
        return listOf(limeArtist1, limeArtist2)
    }

}