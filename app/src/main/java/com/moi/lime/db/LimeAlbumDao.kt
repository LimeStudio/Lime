package com.moi.lime.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moi.lime.vo.LimeAlbum
import io.reactivex.Single

@Dao
interface LimeAlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg limeAlbum: LimeAlbum)

    @Query("SELECT * FROM lime_album")
    fun getAll(): Single<List<LimeAlbum>>

    @Query("DELETE FROM lime_album")
    fun deleteAll()
}