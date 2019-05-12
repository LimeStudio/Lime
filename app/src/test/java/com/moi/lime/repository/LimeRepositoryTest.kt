package com.moi.lime.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDb
import com.moi.lime.util.RxSchedulerRule
import com.moi.lime.util.mock
import com.moi.lime.util.toBean
import com.moi.lime.vo.OnlyCodeBean
import com.moi.lime.vo.SignInByPhoneBean
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import okio.Okio
import org.junit.After
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
    val testDispatcher = TestCoroutineDispatcher()
    @ExperimentalCoroutinesApi
    val testScope = TestCoroutineScope(testDispatcher)

    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var limeRepository: LimeRepository
    private val service = mock<MoiService>()
    private val userManager = mock<UserManager>()


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val db = mock(LimeDb::class.java)
        `when`(service.signInRefresh()).thenReturn(Flowable.just(OnlyCodeBean(200)))
        limeRepository = LimeRepository(userManager, service, db)
    }

    @ExperimentalCoroutinesApi
    @After
    fun testDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSignIn() = testScope.runBlockingTest {
        val inputStream = javaClass
                .getResourceAsStream("/api-response/SignInResponse")
        val source = Okio.buffer(Okio.source(inputStream!!))

        val signInByPhoneBean = source.readString(Charsets.UTF_8).toBean<SignInByPhoneBean>()!!
        `when`(service.signInByPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(signInByPhoneBean)

        val subject = limeRepository.signIn("test", "test")
        assert(subject)
        verify(userManager).saveUser(signInByPhoneBean)
        Mockito.verify(service).signInByPhone("test", "test")
       }
}
