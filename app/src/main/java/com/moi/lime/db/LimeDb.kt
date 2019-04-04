package com.moi.lime.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moi.lime.vo.LimeArtist
import com.moi.lime.vo.LimeMusic
import com.moi.lime.vo.LimeUrl
import com.moi.lime.vo.Profile

@Database(
        entities = [
            Profile::class,
            LimeMusic::class,
            LimeUrl::class,
            LimeArtist::class
        ],
        version = 1
)
abstract class LimeDb : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun limeMusicDao(): LimeMusicDao
    abstract fun limeUrlDao(): LimeUrlDao
    abstract fun limeArtist(): LimeArtistDao
}

