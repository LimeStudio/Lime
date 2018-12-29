package com.moi.lime.core.user

import com.moi.lime.db.ProfileDao
import com.moi.lime.vo.LoginProfile
import com.moi.lime.vo.Profile
import com.moi.lime.vo.SignInByPhoneBean
import io.reactivex.Single
import java.lang.IllegalStateException

class Cardinal(private val profileDao: ProfileDao) : UserManager {

    private var isInit = false
    private var profile: Profile? = null

    override fun init(): Single<Profile> {
        isInit = true
        return profileDao.findUserBySignIn(true)
                .doOnSuccess { profile = it }
    }

    override fun saveUser(signInByPhoneBean: SignInByPhoneBean): Boolean {
        checkStatus()
        if (signInByPhoneBean.profile?.userId != null) {
            val profile = createProfile(signInByPhoneBean.profile)
            profileDao.insert(profile)
            this.profile = profile
            return true
        }
        return false

    }

    override fun getProfile(): Profile? {
        checkStatus()
        return profile
    }

    override fun updateProfile(profile: Profile) {
        checkStatus()
        val newProfile = profile.copy()
        profileDao.insert(newProfile)
        this.profile = newProfile
    }

    override fun cleanUser() {
        checkStatus()
        profileDao.clean()
        profile = null
    }

    override fun isSignIn(): Boolean {
        checkStatus()
        return profile != null
    }

    private fun checkStatus() {
        if (!isInit) {
            throw IllegalStateException("you should call init(), before using this class")
        }
    }

    private fun createProfile(loginProfile: LoginProfile): Profile {
        with(loginProfile) {
            return Profile(true
                    , (userId ?: 0).toString()
                    , 0L
                    , vipType ?: 0
                    , nickname ?: ""
                    , (gender ?: 0).toString()
                    , birthday ?: 0L
                    , (province ?: 0L).toString()
                    , defaultAvatar ?: true
                    , avatarUrl ?: ""
                    , backgroundUrl ?: "")
        }
    }

}
