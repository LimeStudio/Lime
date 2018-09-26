package com.moi.lime.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moi.lime.vo.Profile
import io.reactivex.Flowable

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)

    @Query("SELECT * FROM profile WHERE uid = :uid")
    fun findByUid(uid: String): Flowable<Profile>

    @Query("SELECT * FROM profile WHERE isSignIn = :isSignIn")
    fun findUserBySignIn(isSignIn:Boolean): Flowable<Profile>
}