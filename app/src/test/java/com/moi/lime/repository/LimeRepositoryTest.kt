package com.moi.lime.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.util.RxSchedulerRule
import com.moi.lime.util.mock
import com.moi.lime.util.toBean
import com.moi.lime.vo.OnlyCodeBean
import com.moi.lime.vo.Resource
import com.moi.lime.vo.SignInByPhoneBean
import io.reactivex.Flowable
import okio.Okio
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class LimeRepositoryTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var limeRepository: LimeRepository
    private val service = mock<MoiService>()
    private val userManager = mock<UserManager>()


    @Before
    fun setUp() {
        `when`(service.signInRefresh()).thenReturn(Flowable.just(OnlyCodeBean(200)))
        limeRepository = LimeRepository(userManager, service)
    }

    @Test
    fun testSignIn() {
        val inputStream = javaClass
                .getResourceAsStream("/api-response/SignInResponse")
        val source = Okio.buffer(Okio.source(inputStream!!))

        val signInByPhoneBean = source.readString(Charsets.UTF_8).toBean<SignInByPhoneBean>()
        `when`(service.signInByPhone(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Flowable.just(signInByPhoneBean))

        val data = limeRepository.signIn("test", "test")
        val observer = mock<Observer<Resource<Boolean>>>()
        data.observeForever(observer)
        verify(userManager).saveUser(signInByPhoneBean)
        verify(service).signInByPhone("test", "test")
        verify(observer).onChanged(Resource.success(true))
    }
}