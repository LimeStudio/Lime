package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moi.lime.vo.LimeMusic
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LimeMusicDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadAllTest() {
        val list = createLimeMusicList()
        db.limeMusicDao().insertAll(*list.toTypedArray())
        db.limeMusicDao().getAll()
                .test()
                .assertValue {
                    it == list && it.size==2
                }

    }

    @Test
    fun insertAndDeleteAllTest() {
        val list = createLimeMusicList()
        db.limeMusicDao().insertAll(*list.toTypedArray())
        db.limeMusicDao().deleteAll()
        db.limeMusicDao()
                .getAll()
                .test()
                .assertValue {
                    it.isEmpty()
                }

    }

    private fun createLimeMusicList(): List<LimeMusic> {
        val limeMusic1 = LimeMusic("test1", "1", 100, "test1")
        val limeMusic2 = LimeMusic("test2", "2", 100, "test2")
        return listOf(limeMusic1, limeMusic2)
    }

}