package com.moi.lime.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moi.lime.vo.LimeArtist
import io.reactivex.Single

@Dao
interface LimeArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg limeArtist: LimeArtist)

    @Query("SELECT * FROM lime_artist")
    fun getAll(): Single<List<LimeArtist>>

    @Query("DELETE FROM lime_artist")
    fun deleteAll()
}