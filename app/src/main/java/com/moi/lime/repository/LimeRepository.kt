package com.moi.lime.repository

import androidx.lifecycle.LiveData
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.util.asLiveData
import com.moi.lime.vo.Resource
import com.moi.lime.vo.SignInByPhoneBean
import javax.inject.Inject

class LimeRepository @Inject constructor(private val userManager: UserManager, private val moiService: MoiService) {
    fun signIn(phoneNumber: String, password: String): LiveData<Resource<Boolean>> {
        return moiService.signInByPhone(phoneNumber, password)
                .flatMap { signInByPhoneBean ->
                    moiService.signInRefresh()
                            .map {
                                if (it.code == 200) {
                                    userManager.saveUser(signInByPhoneBean)
                                    true
                                } else {
                                    false
                                }
                            }
                }
                .asLiveData()
    }



}