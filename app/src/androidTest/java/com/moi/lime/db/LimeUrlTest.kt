package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.vo.LimeMusic
import com.moi.lime.vo.LimeUrl
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LimeUrlTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadAllTest() = runBlocking {
        val list = createLimeUrlList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeUrlDao().insertAll(*list.toTypedArray())
        val urls = db.limeUrlDao().getAll()
        assertEquals(2, urls.size)


    }

    @Test
    fun insertAndDeleteAllTest() = runBlocking {
        val list = createLimeUrlList()
        db.limeMusicDao().insertAll(LimeMusic("test", "1", 400, "test"))
        db.limeUrlDao().insertAll(*list.toTypedArray())
        db.limeUrlDao().deleteAll()
        val urls = db.limeUrlDao().getAll()
        assertEquals(0, urls.size)
    }

    private fun createLimeUrlList(): List<LimeUrl> {
        val limeUrl1 = LimeUrl("url", "1", "400", "400", "test1")
        val limeUrl2 = LimeUrl("url", "1", "400", "400", "test2")
        return listOf(limeUrl1, limeUrl2)
    }

}