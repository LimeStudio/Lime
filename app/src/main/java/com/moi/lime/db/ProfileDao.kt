package com.moi.lime.db

import androidx.room.*
import com.moi.lime.vo.Profile
import io.reactivex.Single

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)

    @Query("SELECT * FROM profile WHERE uid = :uid")
    fun findByUid(uid: String): Single<Profile>

    @Query("SELECT * FROM profile WHERE isSignIn = :isSignIn")
    fun findUserBySignIn(isSignIn:Boolean): Single<Profile>

    @Query("DELETE FROM profile")
    fun clean()
}