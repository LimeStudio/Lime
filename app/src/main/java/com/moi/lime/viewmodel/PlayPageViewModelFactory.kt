package com.moi.lime.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moi.lime.core.dispatch.Dispatchers
import com.moi.lime.repository.LimeRepository
import com.moi.lime.ui.play.PlayPageViewModel
import javax.inject.Inject

class PlayPageViewModelFactory @Inject constructor(private val limeRepository: LimeRepository, private val dispatchers: Dispatchers) : ViewModelProvider.NewInstanceFactory() {
    lateinit var currentId: MutableLiveData<String>

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayPageViewModel(limeRepository, currentId, dispatchers) as T
    }
}