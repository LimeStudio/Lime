package com.moi.lime.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDb
import com.moi.lime.util.*
import com.moi.lime.vo.OnlyCodeBean
import com.moi.lime.vo.Resource
import com.moi.lime.vo.SignInByPhoneBean
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okio.Okio
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class LimeRepositoryTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()

    private lateinit var limeRepository: LimeRepository
    private val service = mock<MoiService>()
    private val userManager = mock<UserManager>()


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() = runBlocking {
        val db = mock(LimeDb::class.java)
        `when`(service.signInRefresh()).thenReturn(OnlyCodeBean(200))
        limeRepository = LimeRepository(userManager, service, db, TestDispatchers(testDispatchersRule.testDispatcher))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSignIn() = testDispatchersRule.testScope.runBlockingTest {
        val inputStream = javaClass
                .getResourceAsStream("/api-response/SignInResponse")
        val source = Okio.buffer(Okio.source(inputStream!!))

        val signInByPhoneBean = source.readString(Charsets.UTF_8).toBean<SignInByPhoneBean>()!!
        `when`(service.signInByPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(signInByPhoneBean)

        val subject = limeRepository.signIn("test", "test")
        subject.observeForTesting {
            assertEquals(Resource.success(true), subject.value)
            verify(userManager).saveUser(signInByPhoneBean)
            Mockito.verify(service).signInByPhone("test", "test")
        }

    }

}
