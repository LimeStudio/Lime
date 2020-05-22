package com.moi.lime.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.lime.testing.OpenForTesting
import com.moi.lime.api.MoiService
import com.moi.lime.core.dispatch.Dispatchers
import com.moi.lime.core.exception.NoDataFindException
import com.moi.lime.core.user.UserManager
import com.moi.lime.db.LimeDb
import com.moi.lime.util.MusicMapper
import com.moi.lime.util.resourceLiveData
import com.moi.lime.vo.MusicInformation
import com.moi.lime.vo.Product
import com.moi.lime.vo.Resource
import com.moi.lime.vo.UserPlayLists
import kotlinx.coroutines.delay
import javax.inject.Inject

@OpenForTesting
class LimeRepository @Inject constructor(
        private val userManager: UserManager,
        private val moiService: MoiService,
        private val db: LimeDb,
        private val dispatchers: Dispatchers
) {

    fun signIn(phoneNumber: String, password: String) =
            resourceLiveData(dispatchers.provideIO()) {
                val signInByPhoneBean = moiService.signInByPhone(phoneNumber, password)
                if (signInByPhoneBean.code == 200) {
                    userManager.saveUser(signInByPhoneBean)
                    true
                } else {
                    false
                }
            }

    fun fetchRecommendMusics(shouldLoadFromDb: Boolean): LiveData<Resource<List<MusicInformation>>> {
        return liveData(dispatchers.provideIO()) {
            try {
                emit(Resource.loading(null))
                if (shouldLoadFromDb) {
                    emit(Resource.success(db.musicInformationDao().getAllMusicInformation()))
                } else {
                    val recommendSongsEntity = moiService.fetchRecommendSongs()
                    val recommends = recommendSongsEntity.recommend
                            .map { it.id }
                    val ids = recommends.joinToString(",")
                    val musicList = moiService.fetchMusicUrlById(ids)
                    cleanRecommendDb()
                    MusicMapper(recommendSongsEntity, musicList).saveMusic(db)
                    emit(Resource.success(db.musicInformationDao().getAllMusicInformation()))
                }
            } catch (e: Exception) {
                emit(Resource.error(e, null))
            }
        }

    }

    fun fetchUserList(): LiveData<Resource<UserPlayLists>> {
        return resourceLiveData(dispatchers.provideIO()) {
            moiService.fetchUserPlayLists(userManager.getProfile()?.uid ?: "")
        }
    }


    fun fetchMusicInfoById(id: String): LiveData<Resource<MusicInformation>> {
        return liveData(dispatchers.provideIO()) {
            try {
                emit(Resource.loading(null))
                val result = db.musicInformationDao().getMusicInformationFromMusicId(id)
                if (result == null) {
                    emit(Resource.error(NoDataFindException("Can't found MusicInformationFromMusic"), null))
                } else {
                    emit(Resource.success(result))
                }
            } catch (e: Exception) {
                emit(Resource.error(e, null))
            }
        }
    }


    suspend fun getAllMusicInformation() = db.musicInformationDao().getAllMusicInformation()

    private fun cleanRecommendDb() {
        with(db) {
            limeArtistDao().deleteAll()
            limeAlbumDao().deleteAll()
            limeUrlDao().deleteAll()
            limeMusicDao().deleteAll()
        }
    }

    fun getProducts(): LiveData<Resource<List<Product>>> {
        return liveData(dispatchers.provideIO()) {
            emit(Resource.loading(null))
            delay(3000)
            emit(Resource.success(listOf(
                    Product("product1", 0),
                    Product("product2", 0),
                    Product("product3", 0),
                    Product("product4", 0)
            )))


        }
    }

    fun addProducts(id: String): LiveData<Resource<Product>> {
        return liveData {
            emit(Resource.loading(null))
            delay(3000)
            emit(Resource.success(Product("new", 0)))
        }
    }

    fun addProductsToCart(name: String): LiveData<Resource<Product>> {
        return liveData {
            emit(Resource.loading(null))
            delay(3000)
            emit(Resource.success(Product(name, 0)))
        }
    }
}