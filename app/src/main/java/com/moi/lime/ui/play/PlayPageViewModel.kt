package com.moi.lime.ui.play

import android.util.Log
import androidx.lifecycle.*
import com.lime.testing.OpenForTesting
import com.moi.lime.core.dispatch.Dispatchers
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.toJson
import com.moi.lime.vo.LimeUrl
import com.moi.lime.vo.MusicInformation

@OpenForTesting
class PlayPageViewModel(limeRepository: LimeRepository, var currentMusicId: MutableLiveData<String>, dispatchers: Dispatchers) : ViewModel() {

    val playPageDataLists = liveData(dispatchers.provideIO()) {
        val result = mapToPlayPageData(limeRepository.getAllMusicInformation())
        emit(result)
    }

    val currentMusic = MediatorLiveData<PlayPageData>()

    init {
        currentMusic.apply {
            addSource(playPageDataLists) { list ->
                currentMusic.value = list.find {
                    it.id == currentMusicId.value
                }
            }
            addSource(currentMusicId) {
                val list = playPageDataLists.value
                if (list != null) {
                    currentMusic.value = list.find {
                        it.id == currentMusicId.value
                    }
                }
            }
        }
    }

    fun currentMusicIdChange(position: Int) {
        val list = playPageDataLists.value
        if (list != null) {
            currentMusicId.value = list[position].id
        }

    }

    fun getMusicImageUrlByPosition(position: Int): String? {
        return playPageDataLists.value?.get(position)?.musicImageUrl
    }


    private fun mapToPlayPageData(musicInformationList: List<MusicInformation>): List<PlayPageData> {
        val json = musicInformationList.take(2).toJson()
        return musicInformationList.map {
            val musicUrl = it.limeUrls
            val musicName: String = it.limeMusic?.name ?: "unknown"

            val artists = it.limeArtist
            val musicArtist = artists
                    .map { limeArtist ->
                        limeArtist.name ?: "unknown"
                    }
                    .reduce { acc, name ->
                        "${acc}/${name}"
                    }
            val musicImageUrl: String = it.limeAlbum.firstOrNull()?.picUrl ?: ""

            val musicAlbum: String = it.limeAlbum
                    .map { limeArtist ->
                        limeArtist.name ?: "unknown"
                    }
                    .reduce { acc, name ->
                        "${acc}/${name}"
                    }
            PlayPageData(it.limeMusic?.id
                    ?: "-1", musicUrl, musicName, musicArtist, musicImageUrl, musicAlbum)
        }


    }


    data class PlayPageData(val id: String, val musicUrl: List<LimeUrl>, val musicName: String, val musicArtist: String, val musicImageUrl: String, val musicAlbum: String)
}