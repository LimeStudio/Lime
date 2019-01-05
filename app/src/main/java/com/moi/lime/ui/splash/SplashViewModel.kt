package com.moi.lime.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moi.lime.core.user.UserManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(val userManager: UserManager) : ViewModel() {
    val isSignInValue = MutableLiveData<Boolean>()
    fun init() {
        val disposable = Single.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isSignInValue.value = userManager.isSignIn()
                }, {

                })
    }
}