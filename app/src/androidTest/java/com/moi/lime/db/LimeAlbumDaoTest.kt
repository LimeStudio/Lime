package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.vo.LimeAlbum
import com.moi.lime.vo.LimeMusic
import org.junit.Rule
import org.junit.Test


class LimeAlbumDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadAllTest() {
        val list = createLimeAlbumList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeAlbumDao().insertAll(*list.toTypedArray())
        db.limeAlbumDao().getAll()
                .test()
                .assertValue {
                    it == list && it.size == 2
                }

    }

    @Test
    fun insertAndDeleteAllTest() {
        val list = createLimeAlbumList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeAlbumDao().insertAll(*list.toTypedArray())
        db.limeAlbumDao().deleteAll()
        db.limeAlbumDao()
                .getAll()
                .test()
                .assertValue {
                    it.isEmpty()
                }

    }

    private fun createLimeAlbumList(): List<LimeAlbum> {
        val limeAlbum1 = LimeAlbum("1","test","1","test",100,"1","test","test","test")
        val limeAlbum2 = LimeAlbum("1","test","2","test",100,"1","test","test","test")
        return listOf(limeAlbum1, limeAlbum2)
    }

}