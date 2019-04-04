package com.moi.lime.vo

import androidx.room.Embedded
import androidx.room.Relation

class MusicInfomation {
    @Embedded
    var limeMusic:LimeMusic?=null

    @Relation(parentColumn = "id",
            entityColumn =  "musicId")
    var limeUrls:List<LimeUrl> = mutableListOf()

    @Relation(parentColumn = "id",
            entityColumn =  "musicId")
    var limeAlbum:List<LimeAlbum> = mutableListOf()

    @Relation(parentColumn = "id",
            entityColumn =  "musicId")
    var limeArtist:List<LimeArtist> = mutableListOf()
}