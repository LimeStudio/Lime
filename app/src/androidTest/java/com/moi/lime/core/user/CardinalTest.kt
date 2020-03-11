package com.moi.lime.core.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.createProfile
import com.moi.lime.util.loadJsonFromFilePath
import com.moi.lime.util.toBean
import com.moi.lime.vo.SignInByPhoneBean
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okio.Okio
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertFalse
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
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
    fun testInitAndSave() = runBlocking {
        userManager.init()
        val result = userManager.saveUser(loadJsonFromFilePath("/json/SignInJson", javaClass).toBean()!!)
        assertTrue(result)

    }

    @Test
    fun testInitAndSaveFailed() = runBlocking {
        userManager.init()
        val signInByPhoneBean: SignInByPhoneBean = loadJsonFromFilePath("/json/SignInJson", javaClass).toBean()!!
        val profile = signInByPhoneBean.profile?.copy(userId = null)
        val result = userManager.saveUser(signInByPhoneBean.copy(profile = profile))
        assertFalse(result)

    }

    @Test
    fun testInitAndGetProfile() = runBlocking {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        userManager.init()
        assertTrue(userManager.getProfile()?.uid == profile.uid)

    }

    @Test
    fun testUpdateProfile() = runBlocking {
        val profile = createProfile(true)
        db.profileDao().insert(profile)

        userManager.init()
        val newProfile = profile.copy(uid = "test")
        userManager.updateProfile(newProfile)

        assertTrue(userManager.getProfile()?.uid == newProfile.uid)
    }

    @Test
    fun testCleanUser() = runBlocking {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        userManager.init()
        userManager.cleanUser()
        assertNull(db.profileDao()
                .findUserBySignIn(true))
    }

    @Test
    fun testIsSignIn() = runBlocking {
        userManager.init()
        val profile = createProfile(true)
        userManager.updateProfile(profile)
        assertTrue(userManager.isSignIn())


    }
}