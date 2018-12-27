package com.moi.lime.core.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.createProfile
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardinalTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var userManager: UserManager

    @Before
    fun setUp() {
        userManager = Cardinal(db.profileDao())
    }

    @Test
    fun testInitAndSave() {
        userManager.init()
                .test()
                .assertError(EmptyResultSetException::class.java)

        val profile = createProfile(true)
        db.profileDao().insert(profile)
    }

    @Test
    fun testGetProfile() {
    }

    @Test
    fun testUpdateProfile() {
    }

    @Test
    fun testCleanUser() {
    }

    @Test
    fun testIsSignIn() {
    }
}