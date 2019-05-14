package com.moi.lime.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.EmptyResultSetException
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moi.lime.util.TestDispatchersRule
import com.moi.lime.util.createProfile
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ProfileDaoTest : LimeDbTest() {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()


    @Test
    fun insertAndLoadWithUid() = testDispatchersRule.testScope.runBlockingTest {
        val profile = createProfile()
        db.profileDao().insert(profile)
        val profileFromDb = db.profileDao().findByUid("123456")
        assertEquals("123456", profileFromDb?.uid)
    }

    @Test
    fun insertAndLoadWithSignIn() = testDispatchersRule.testScope.runBlockingTest {
        val profile = createProfile(true)
        db.profileDao().insert(profile)
        val dbProfile = db.profileDao().findUserBySignIn(true)
        assertTrue { dbProfile?.isSignIn?: false }

    }

    @Test
    fun insertAndClean() = testDispatchersRule.testScope.runBlockingTest {

        val profile = createProfile(true)
        db.profileDao().insert(profile)

        val dbProfile =  db.profileDao().findUserBySignIn(true)
        assertTrue { dbProfile?.isSignIn?: false }
        db.profileDao().clean()

            val dbProfile2 =  db.profileDao().findUserBySignIn(true)
            assertNull(dbProfile2)

    }

}