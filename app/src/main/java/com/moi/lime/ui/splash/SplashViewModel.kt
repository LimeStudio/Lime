package com.moi.lime.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lime.testing.OpenForTesting
import com.moi.lime.core.dispatch.Dispatchers
import com.moi.lime.core.user.UserManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
class SplashViewModel @Inject constructor(private val userManager: UserManager, private val dispatchers: Dispatchers) : ViewModel() {
    val isSignInValue = MutableLiveData<Boolean>()
    @SuppressLint("CheckResult")
    fun init() {
        viewModelScope.launch(dispatchers.provideMain()) {
            delay(2000)
            isSignInValue.value = userManager.isSignIn()
        }
    }
}