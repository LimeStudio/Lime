package com.moi.lime.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moi.lime.vo.LimeUrl

@Dao
interface LimeUrlDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg limeUrl: LimeUrl)

    @Query("SELECT * FROM lime_url")
    suspend fun getAll(): List<LimeUrl>

    @Query("DELETE FROM lime_url")
    fun deleteAll()
}