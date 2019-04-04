package com.moi.lime.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moi.lime.vo.*

@Database(
        entities = [
            Profile::class,
            LimeMusic::class,
            LimeUrl::class,
            LimeArtist::class,
            LimeAlbum::class
        ],
        version = 1
)
abstract class LimeDb : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun limeMusicDao(): LimeMusicDao
    abstract fun limeUrlDao(): LimeUrlDao
    abstract fun limeArtistDao(): LimeArtistDao
    abstract fun limeAlbumDao(): LimeAlbumDao
    abstract fun musicInformationDao(): MusicInformationDao
}

