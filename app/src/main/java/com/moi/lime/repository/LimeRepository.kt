package com.moi.lime.repository

import androidx.lifecycle.LiveData
import com.moi.lime.api.MoiService
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDb
import com.moi.lime.util.asLiveData
import com.moi.lime.vo.*
import io.reactivex.Flowable
import javax.inject.Inject

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

    fun fetchRecommendMusics(isNewDay: Boolean): LiveData<Resource<List<MusicInformation>>> {
        return if (isNewDay) {
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
                    mapAndSaveMusic(recommendSongsEntity!!)
                    mapAndSaveMusicUrl(it)
                    mapAndSaveMusicAlbum(recommendSongsEntity!!)
                    mapAndSaveMusicArtist(recommendSongsEntity!!)
                    db.musicInformationDao().getAllMusicInformation().toFlowable()
                }

    }


    private fun mapAndSaveMusic(recommendSongsEntity: RecommendSongsEntity) {
        val limeMusics = recommendSongsEntity.recommend
                .map {
                    LimeMusic(it.name, it.id.toString(), it.duration, it.reason)
                }
        db.limeMusicDao().insertAll(*limeMusics.toTypedArray())
    }

    private fun mapAndSaveMusicUrl(musicUrlsEntity: MusicUrlsEntity) {
        val limeUrls = musicUrlsEntity.data.map {
            LimeUrl(it.id.toString(), it.br.toString(), it.size.toString(), it.encodeType)
        }
        db.limeUrlDao().insertAll(*limeUrls.toTypedArray())
    }

    private fun mapAndSaveMusicAlbum(recommendSongsEntity: RecommendSongsEntity) {
        val limeAlbums = recommendSongsEntity.recommend.map {
            with(it.album) {
                LimeAlbum(it.id.toString(), name, id.toString(), type, size, picId.toString(), blurPicUrl, picUrl, company)
            }
        }
        db.limeAlbumDao().insertAll(*limeAlbums.toTypedArray())
    }

    private fun mapAndSaveMusicArtist(recommendSongsEntity: RecommendSongsEntity) {
        val limeArtists = recommendSongsEntity.recommend.flatMap { recommendItem ->
            recommendItem.artists.map {
                Pair(it, recommendItem.id)
            }
        }
                .map { artist ->
                    with(artist.first) {
                        LimeArtist(artist.second.toString(), id.toString(), picUrl, imgVUrl)
                    }

                }
        db.limeArtistDao().insertAll(*limeArtists.toTypedArray())
    }

}