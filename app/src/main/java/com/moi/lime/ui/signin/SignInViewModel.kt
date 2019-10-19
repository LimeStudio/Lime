package com.moi.lime.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.lime.testing.OpenForTesting
import com.moi.lime.core.livedata.SingleLiveEvent
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.AbsentLiveData
import javax.inject.Inject

@OpenForTesting
class SignInViewModel @Inject constructor(private val limeRepository: LimeRepository) : ViewModel() {

    val phoneNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val  loginInfo = SingleLiveEvent<Unit>()
    val loginResource = loginInfo.switchMap {
        if (phoneNumber.value != null && password.value != null) {
            limeRepository.signIn(phoneNumber.value!!, password.value!!)
        } else {
            AbsentLiveData.create()
        }
    }

    fun login(){
        loginInfo.value = Unit
    }

}