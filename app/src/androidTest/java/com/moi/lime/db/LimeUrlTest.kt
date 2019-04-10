package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.vo.LimeMusic
import com.moi.lime.vo.LimeUrl
import org.junit.Rule
import org.junit.Test

class LimeUrlTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadAllTest() {
        val list = createLimeUrlList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeUrlDao().insertAll(*list.toTypedArray())
        db.limeUrlDao().getAll()
                .test()
                .assertValue {
                    it == list && it.size == 2
                }

    }

    @Test
    fun insertAndDeleteAllTest() {
        val list = createLimeUrlList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeUrlDao().insertAll(*list.toTypedArray())
        db.limeUrlDao().deleteAll()
        db.limeUrlDao()
                .getAll()
                .test()
                .assertValue {
                    it.isEmpty()
                }

    }

    private fun createLimeUrlList(): List<LimeUrl> {
        val limeUrl1 = LimeUrl("url", "1", "400", "400", "test1")
        val limeUrl2 = LimeUrl("url", "1", "400", "400", "test2")
        return listOf(limeUrl1, limeUrl2)
    }

}