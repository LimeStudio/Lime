package com.moi.lime.ui.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.lime.testing.OpenForTesting
import com.moi.lime.core.livedata.SingleLiveEvent
import com.moi.lime.core.user.UserManager
import com.moi.lime.repository.LimeRepository
import com.moi.lime.vo.Resource
import com.moi.lime.vo.Status
import com.moi.lime.vo.UserPlayLists
import javax.inject.Inject

@OpenForTesting
class ProfileFragmentViewModel @Inject constructor(userManager: UserManager, limeRepository: LimeRepository) : ViewModel() {
    val profile = userManager.getProfile()
    val fetchPlaylistsTrigger = SingleLiveEvent<Unit>()
    val allPlaylistsResource: LiveData<Resource<UserPlayLists>> = fetchPlaylistsTrigger.switchMap {
        limeRepository.fetchUserList()
    }
    val profilePlaylists = allPlaylistsResource.map { resource ->
        if (resource.status == Status.SUCCESS) {
            val playlist = resource.data?.playlist?.filter { it.creator?.userId.toString() == userManager.getProfile()?.uid }
            Resource.success(playlist)
        } else {
            Resource(resource.status, null, resource.throwable)
        }
    }

    init {
        fetchPlaylistsTrigger.value = Unit
    }
}