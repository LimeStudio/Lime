package com.moi.lime.db

import androidx.room.Dao
import androidx.room.Query
import com.moi.lime.vo.MusicInformation

@Dao
interface MusicInformationDao {

    @Query("SELECT * from lime_music")
    suspend fun getAllMusicInformation(): List<MusicInformation>

    @Query("SELECT * FROM lime_music WHERE id == :musicId")
    suspend fun getMusicInformationFromMusicId(musicId: String): MusicInformation?


}