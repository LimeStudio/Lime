package com.moi.lime.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moi.lime.vo.Profile

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)

    @Query("SELECT * FROM profile WHERE uid = :uid")
    suspend fun findByUid(uid: String): Profile?

    @Query("SELECT * FROM profile WHERE isSignIn = :isSignIn")
    suspend fun findUserBySignIn(isSignIn: Boolean): Profile?

    @Query("DELETE FROM profile")
    fun clean()
}