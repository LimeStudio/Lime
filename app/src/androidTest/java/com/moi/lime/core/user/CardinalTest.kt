package com.moi.lime.core.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.createProfile
import com.moi.lime.util.toBean
import okio.Okio
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardinalTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userManager: UserManager

    @Before
    fun setUp() {
        userManager = Cardinal(db.profileDao())
    }

    @Test
    fun testInitAndSave() {
        userManager.init()
                .test()
                .assertError(EmptyResultSetException::class.java)
        val result = userManager.saveUser(getSignInJson().toBean()!!)
        assert(result)

    }

    @Test
    fun testInitAndGetProfile() {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        userManager.init()
                .test()
                .assertValue {
                    it.uid == profile.uid
                }
        assert(userManager.getProfile()?.uid == profile.uid)

    }

    @Test
    fun testUpdateProfile() {
        val profile = createProfile(true)
        db.profileDao().insert(profile)

        userManager.init()
                .test()
                .assertValue {
                    it.uid == profile.uid
                }

        val newProfile = createProfile(true).copy(uid = "test")

        userManager.updateProfile(newProfile)

        assert(userManager.getProfile()?.uid == newProfile.uid)
        db.profileDao().findUserBySignIn(true)
                .map { it.uid }
                .test()
                .assertValue(newProfile.uid)
    }

    @Test
    fun testCleanUser() {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        userManager.init()
                .test()
                .assertValue {
                    it.uid == profile.uid
                }
        userManager.cleanUser()
        db.profileDao()
                .findUserBySignIn(true)
                .test()
                .assertError(EmptyResultSetException::class.java)

    }

    @Test
    fun testIsSignIn() {
        userManager.init()
                .test()
        assert(!userManager.isSignIn())

        val profile = createProfile(true)
        userManager.updateProfile(profile)

        assert(userManager.isSignIn())


    }

    private fun getSignInJson(): String {
        val inputStream = javaClass
                .getResourceAsStream("/json/SignInJson")
        val source = Okio.buffer(Okio.source(inputStream!!))
        return source.readString(Charsets.UTF_8)
    }
}