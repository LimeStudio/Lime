package com.moi.lime.repository

import androidx.lifecycle.LiveData
import com.lime.testing.OpenForTesting
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDb
import com.moi.lime.util.MusicMapper
import com.moi.lime.util.asLiveData
import com.moi.lime.vo.MusicInformation
import com.moi.lime.vo.RecommendSongsEntity
import com.moi.lime.vo.Resource
import io.reactivex.Flowable
import javax.inject.Inject

@OpenForTesting
class LimeRepository @Inject constructor(private val userManager: UserManager, private val moiService: MoiService, private val db: LimeDb) {
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

    fun fetchRecommendMusics(shouldLoadFromDb: Boolean): LiveData<Resource<List<MusicInformation>>> {
        return if (!shouldLoadFromDb) {
            fetchAndSaveCommendMusics().asLiveData()
        } else {
            db.musicInformationDao().getAllMusicInformation().asLiveData()
        }
    }

    private fun fetchAndSaveCommendMusics(): Flowable<List<MusicInformation>> {
        var recommendSongsEntity: RecommendSongsEntity? = null
        return moiService.fetchRecommendSongs()
                .flatMap {
                    recommendSongsEntity = it
                    Flowable.fromIterable(it.recommend)
                }
                .map {
                    it.id
                }
                .toList()
                .toFlowable()
                .flatMap {
                    val ids = it.joinToString(separator = ",")
                    moiService.fetchMusicUrlById(ids)
                }
                .flatMap {
                    MusicMapper(recommendSongsEntity!!, it).saveMusic(db)
                    db.musicInformationDao().getAllMusicInformation().toFlowable()
                }

    }


}