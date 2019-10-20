package com.moi.lime.ui.signin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class SignInViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val limeRepository = mock<LimeRepository>()

    @Test
    fun testCallSignIn(){
        val viewModel = SignInViewModel(limeRepository)
        viewModel.loginResource.observeForever { mock() }
        viewModel.phoneNumber.value ="test"
        viewModel.password.value ="test"
        viewModel.login()
        verify(limeRepository).signIn("test","test")
    }
}