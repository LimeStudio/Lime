package com.moi.lime.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moi.lime.vo.LimeArtist

@Dao
interface LimeArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg limeArtist: LimeArtist)

    @Query("SELECT * FROM lime_artist")
    suspend fun getAll(): List<LimeArtist>

    @Query("DELETE FROM lime_artist")
    fun deleteAll()
}