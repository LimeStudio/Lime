package com.moi.lime.ui.play

import androidx.lifecycle.ViewModel
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.Logger
import javax.inject.Inject

class PlayPageFragmentViewModel @Inject constructor(private val limeRepository: LimeRepository) : ViewModel() {

    init {
        Logger.INS.d("dadada")
    }

}