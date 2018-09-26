package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.moi.lime.util.createProfile
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProfileDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadWithUid() {
        val profile = createProfile()
        db.profileDao().insert(profile)
        db.profileDao()
                .findByUid("123456")
                .test()
                .assertValue {
                    it.uid == "123456"
                }
    }

    @Test
    fun insertAndLoadWithSignIn() {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        db.profileDao()
                .findUserBySignIn(true)
                .test()
                .assertValue { it.isSignIn }
    }

}