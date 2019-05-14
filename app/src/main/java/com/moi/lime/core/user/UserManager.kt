package com.moi.lime.core.user

import com.moi.lime.vo.Profile
import com.moi.lime.vo.SignInByPhoneBean
import io.reactivex.Single

interface UserManager {
    fun saveUser(signInByPhoneBean: SignInByPhoneBean): Boolean
    fun getProfile(): Profile?
    fun updateProfile(profile: Profile)
    fun cleanUser()
    fun isSignIn(): Boolean
    suspend fun init()
}