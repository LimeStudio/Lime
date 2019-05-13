package com.moi.lime.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.moi.lime.core.livedata.SingleLiveEvent
import com.moi.lime.repository.LimeRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val limeRepository: LimeRepository) : ViewModel() {

    val loginInfo = SingleLiveEvent<Pair<String, String>>()
    val loginResource = loginInfo.switchMap {
        limeRepository.signIn(it.first,it.second)
    }
}