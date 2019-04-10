package com.moi.lime.vo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.moi.lime.util.millisecondToMinute

@Entity(tableName = "lime_music")
data class LimeMusic(
        val name: String?,
        @PrimaryKey
        val id: String,
        val duration: Long,
        val reason: String?){
    fun getLength()=millisecondToMinute(duration)
}


@Entity(foreignKeys = [ForeignKey(entity = LimeMusic::class,
        parentColumns = ["id"],
        childColumns = ["musicId"],
        onDelete = CASCADE)], tableName = "lime_url")
data class LimeUrl(
        val url: String?,
        val musicId: String?,
        val br: String?,
        val size: String?,
        val encodeType: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Entity(foreignKeys = [ForeignKey(entity = LimeMusic::class,
        parentColumns = ["id"],
        childColumns = ["musicId"],
        onDelete = CASCADE)], tableName = "lime_artist")
data class LimeArtist(
        val name: String?,
        val musicId: String,
        val artistId: String,
        val picUrl: String?,
        val img1vUrl: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Entity(foreignKeys = [ForeignKey(entity = LimeMusic::class,
        parentColumns = ["id"],
        childColumns = ["musicId"],
        onDelete = CASCADE)], tableName = "lime_album")
data class LimeAlbum(
        val musicId: String,
        val name: String?,
        val id: String,
        val type: String?,
        val size: Int,
        val picId: String?,
        val blurPicUrl: String?,
        val picUrl: String?,
        val company: String?

) {
    @PrimaryKey(autoGenerate = true)
    var albumId: Long = 0
}

