package com.moi.lime.core.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moi.lime.db.LimeDbTest
import com.moi.lime.util.TestDispatchersRule
import com.moi.lime.util.createProfile
import com.moi.lime.util.toBean
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okio.Okio
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CardinalTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userManager: UserManager

    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()

    @Before
    fun setUp() {
        userManager = Cardinal(db.profileDao())
    }

    @Test
    fun testInitAndSave() = testDispatchersRule.testScope.runBlockingTest {
        userManager.init()
        val result = userManager.saveUser(getSignInJson().toBean()!!)
        assertTrue(result)

    }

    @Test
    fun testInitAndGetProfile() = testDispatchersRule.testScope.runBlockingTest {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        userManager.init()
        assertTrue(userManager.getProfile()?.uid == profile.uid)

    }

    @Test
    fun testUpdateProfile() = testDispatchersRule.testScope.runBlockingTest {
        val profile = createProfile(true)
        db.profileDao().insert(profile)

        userManager.init()
        val newProfile = profile.copy(uid = "test")
        userManager.updateProfile(newProfile)

        assertTrue(userManager.getProfile()?.uid == newProfile.uid)
    }

    @Test
    fun testCleanUser() = testDispatchersRule.testScope.runBlockingTest {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        userManager.init()
        userManager.cleanUser()
        assertNull(db.profileDao()
                .findUserBySignIn(true))
    }

    @Test
    fun testIsSignIn() = testDispatchersRule.testScope.runBlockingTest {
        userManager.init()
        val profile = createProfile(true)
        userManager.updateProfile(profile)
        assertTrue(userManager.isSignIn())


    }

    private fun getSignInJson(): String {
        val inputStream = javaClass
                .getResourceAsStream("/json/SignInJson")
        val source = Okio.buffer(Okio.source(inputStream!!))
        return source.readString(Charsets.UTF_8)
    }
}