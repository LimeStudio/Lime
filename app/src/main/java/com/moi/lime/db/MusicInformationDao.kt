package com.moi.lime.db

import androidx.room.Dao
import androidx.room.Query
import com.moi.lime.vo.MusicInformation
import io.reactivex.Single

@Dao
interface MusicInformationDao {
    @Query("SELECT * from lime_music")
    fun getAllMusicInformation(): Single<List<MusicInformation>>
}