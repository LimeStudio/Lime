package com.moi.lime.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.moi.lime.core.livedata.SingleLiveEvent
import com.moi.lime.core.user.UserManager
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.AbsentLiveData
import com.moi.lime.vo.Resource
import com.moi.lime.vo.SignInByPhoneBean
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val limeRepository: LimeRepository) : ViewModel() {
    val loginInfo = SingleLiveEvent<Pair<String, String>>()
    val loginResource: LiveData<Resource<Boolean>> =
            Transformations.switchMap(loginInfo) {
                return@switchMap if (it.first.isNotEmpty() && it.second.isNotEmpty()) {
                    limeRepository.signIn(it.first, it.second)
                } else {
                    AbsentLiveData.create()
                }
            }

}