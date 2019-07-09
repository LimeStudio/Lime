package com.moi.lime.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDb
import com.moi.lime.util.*
import com.moi.lime.vo.OnlyCodeBean
import com.moi.lime.vo.Resource
import com.moi.lime.vo.SignInByPhoneBean
import com.moi.lime.vo.UserPlayLists
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody
import okio.Okio
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import retrofit2.HttpException
import retrofit2.Response
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
    fun testFetchUserPlayList() = testDispatchersRule.testScope.runBlockingTest {

        val result = UserPlayLists(1, null, true)

        `when`(service.fetchUserPlayLists(ArgumentMatchers.anyString()))
                .thenReturn(result)

        val subject = limeRepository.fetchUserList()
        subject.observeForTesting {
            assertEquals(Resource.success(result), subject.value)
            verify(service).fetchUserPlayLists(ArgumentMatchers.anyString())
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testFetchUserPlayListFailed() = testDispatchersRule.testScope.runBlockingTest {

        val testException = HttpException(Response.error<String>(400, ResponseBody.create(null, "{}")))
        `when`(service.fetchUserPlayLists(ArgumentMatchers.anyString())).thenThrow(testException)

        val subject = limeRepository.fetchUserList()
        subject.observeForTesting {
            assertEquals(Resource.error(testException, null), subject.value)
            verify(service).fetchUserPlayLists(ArgumentMatchers.anyString())
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSignInSuccess() = testDispatchersRule.testScope.runBlockingTest {
        val signInByPhoneBean = loadJsonFromFilePath("/api-response/SignInResponse", javaClass).toBean<SignInByPhoneBean>()!!
        `when`(service.signInByPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(signInByPhoneBean)

        val subject = limeRepository.signIn("test", "test")
        subject.observeForTesting {
            assertEquals(Resource.success(true), subject.value)
            verify(userManager).saveUser(signInByPhoneBean)
            verify(service).signInByPhone("test", "test")
        }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSignInFailed() = testDispatchersRule.testScope.runBlockingTest {
        val inputStream = javaClass
                .getResourceAsStream("/api-response/SignInResponse")
        val source = Okio.buffer(Okio.source(inputStream!!))

        val signInByPhoneBean = loadJsonFromFilePath("/api-response/SignInResponse", javaClass).toBean<SignInByPhoneBean>()!!
        `when`(service.signInByPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(signInByPhoneBean.copy(code = 400))

        val subject = limeRepository.signIn("test", "test")
        subject.observeForTesting {
            assertEquals(Resource.success(false), subject.value)
            verify(userManager, never()).saveUser(signInByPhoneBean)
            Mockito.verify(service).signInByPhone("test", "test")
        }

    }


}
