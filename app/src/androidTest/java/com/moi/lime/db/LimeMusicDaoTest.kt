package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moi.lime.vo.LimeMusic
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class LimeMusicDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadAllTest() = runBlocking {
        val list = createLimeMusicList()
        db.limeMusicDao().insertAll(*list.toTypedArray())
        val music = db.limeMusicDao().getAll()
        assertEquals(2, music.size)

    }

    @Test
    fun insertAndDeleteAllTest() = runBlocking {
        val list = createLimeMusicList()
        db.limeMusicDao().insertAll(*list.toTypedArray())
        db.limeMusicDao().deleteAll()
        val music = db.limeMusicDao().getAll()
        assertEquals(0, music.size)
    }

    private fun createLimeMusicList(): List<LimeMusic> {
        val limeMusic1 = LimeMusic("test1", "1", 100, "test1")
        val limeMusic2 = LimeMusic("test2", "2", 100, "test2")
        return listOf(limeMusic1, limeMusic2)
    }

}