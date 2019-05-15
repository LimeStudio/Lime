package com.moi.lime.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moi.lime.vo.LimeMusic

@Dao
interface LimeMusicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg limeMusic: LimeMusic)

    @Query("SELECT * FROM lime_music")
    suspend fun getAll(): List<LimeMusic>

    @Query("DELETE FROM lime_music")
    fun deleteAll()
}