package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.vo.LimeAlbum
import com.moi.lime.vo.LimeMusic
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals


class LimeAlbumDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadAllTest() = runBlocking {
        val list = createLimeAlbumList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeAlbumDao().insertAll(*list.toTypedArray())
        val albums = db.limeAlbumDao().getAll()
        assertEquals(2, albums.size)
    }

    @Test
    fun insertAndDeleteAllTest() = runBlocking {
        val list = createLimeAlbumList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeAlbumDao().insertAll(*list.toTypedArray())
        db.limeAlbumDao().deleteAll()
        val albums = db.limeAlbumDao().getAll()
        assertEquals(0, albums.size)


    }

    private fun createLimeAlbumList(): List<LimeAlbum> {
        val limeAlbum1 = LimeAlbum("1", "test", "1", "test", 100, "1", "test", "test", "test")
        val limeAlbum2 = LimeAlbum("1", "test", "2", "test", 100, "1", "test", "test", "test")
        return listOf(limeAlbum1, limeAlbum2)
    }

}