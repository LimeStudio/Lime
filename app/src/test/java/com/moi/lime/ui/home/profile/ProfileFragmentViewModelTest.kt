package com.moi.lime.ui.home.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.moi.lime.core.user.UserManager
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.mock
import com.moi.lime.util.observeForTesting
import com.moi.lime.vo.PlaylistItem
import com.moi.lime.vo.Resource
import com.moi.lime.vo.UserPlayLists
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.internal.verification.Times

class ProfileFragmentViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val limeRepository = mock<LimeRepository>()

    private val userManager = mock<UserManager>()

    @Test
    fun testCallRepo() {
        val profileFragmentViewModel = ProfileFragmentViewModel(userManager, limeRepository)
        profileFragmentViewModel.allPlaylistsResource.observeForever { Unit }
        Mockito.verify(limeRepository, Times(1)).fetchUserList()

    }

    @Test
    fun testFilter() {
        val profileFragmentViewModel = ProfileFragmentViewModel(userManager, limeRepository)
        val userPlayLists = UserPlayLists(200, null, false)
        (profileFragmentViewModel.allPlaylistsResource as MutableLiveData).value = Resource.success(userPlayLists)
        profileFragmentViewModel.profilePlaylists.observeForTesting {
            assertEquals(Resource.success(null), profileFragmentViewModel.profilePlaylists.value)
        }

    }
}