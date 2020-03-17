package com.moi.lime.ui.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.Logger
import com.moi.lime.vo.LimeUrl
import com.moi.lime.vo.MusicInformation
import com.moi.lime.vo.Resource
import com.moi.lime.vo.Status
import javax.inject.Inject

class PlayPageViewModel(limeRepository: LimeRepository, private val currentMusicId: String) : ViewModel() {

    val currentMusic = limeRepository.fetchPlayPageDataById(currentMusicId)

    data class PlayPageData(val musicUrl: List<LimeUrl>, val musicName: String, val musicArtist: String, val musicImageUrl: String, val musicAlbum: String)
}