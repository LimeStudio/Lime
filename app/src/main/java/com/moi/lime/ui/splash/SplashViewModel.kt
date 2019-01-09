package com.moi.lime.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lime.testing.OpenForTesting
import com.moi.lime.core.user.UserManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OpenForTesting
class SplashViewModel @Inject constructor(private val userManager: UserManager) : ViewModel() {
    val isSignInValue = MutableLiveData<Boolean>()
    @SuppressLint("CheckResult")
    fun init() {
        Single.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isSignInValue.value = userManager.isSignIn()
                }, {

                })
    }
}