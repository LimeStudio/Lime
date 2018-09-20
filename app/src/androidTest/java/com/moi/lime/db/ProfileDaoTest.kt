package com.moi.lime.db

import androidx.test.runner.AndroidJUnit4
import com.moi.lime.RxSchedulersOverrideRule
import com.moi.lime.vo.Profile
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ProfileDaoTest : LimeDbTest() {
    //    @Rule
//    @JvmField
//    var mRxSchedulersOverrideRule = RxSchedulersOverrideRule()
    @Test
    fun insertAndLoadWithUid() {
        asyncToSync()
        val profile = createProfile()
        db.profileDao().insert(profile)
        db.profileDao().findByUid("123456").subscribe(
                {
                    assertNotNull(it)
                }, {
            assertNotNull(it)
        },{
            print("aaaa")
        }
        )

        //   assertNotNull(A)

    }

    private fun createProfile(isSignIn: Boolean = false): Profile {
        return Profile(
                isSignIn,
                "123456",
                1234556,
                1,
                "test",
                "1",
                123456,
                "1",
                true,
                "1",
                "1")
    }

    fun asyncToSync() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler {
            return@setIoSchedulerHandler Schedulers.trampoline()
        }
    }
}