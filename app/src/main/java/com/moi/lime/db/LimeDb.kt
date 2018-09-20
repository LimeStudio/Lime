package com.moi.lime.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moi.lime.vo.Profile

@Database(
        entities = [Profile::class],
        version = 1
)
abstract class LimeDb : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}