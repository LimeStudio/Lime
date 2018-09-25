package com.moi.lime.db

import com.moi.lime.util.LiveDataTestUtil
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.moi.lime.util.createProfile
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
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
        val loaded = LiveDataTestUtil.getValue(db.profileDao().findByUid("123456"))
        assertThat(loaded.uid, CoreMatchers.`is`("123456"))
    }

    @Test
    fun insertAndLoadWithSignIn() {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        val loaded = LiveDataTestUtil.getValue(db.profileDao().findUserBySignIn(true))
        assertThat(loaded.isSignIn,CoreMatchers.`is`(true))
    }

}