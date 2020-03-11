package com.moi.lime.ui.home.recommend

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.mock
import com.moi.lime.vo.MusicInformation
import com.moi.lime.vo.Resource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class RecommendFragmentViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val loadingRecommendSwitcher: LoadingRecommendSwitcher = mock()

    private val limeRepository = mock<LimeRepository>()

    @Test
    fun testCallRepo() {
        val resource = Resource.success(null)
        val liveData = MutableLiveData<Resource<List<MusicInformation>>>(resource)
        `when`(limeRepository.fetchRecommendMusics(ArgumentMatchers.anyBoolean())).thenReturn(liveData)
        val recommendFragmentViewModel = RecommendFragmentViewModel(limeRepository, loadingRecommendSwitcher)
        recommendFragmentViewModel.recommendResource.observeForever(mock())
        verify(limeRepository).fetchRecommendMusics(ArgumentMatchers.anyBoolean())
        verify(loadingRecommendSwitcher).refreshState(resource)
    }

    @Test
    fun testFetchRecommendMusics() {
        `when`(loadingRecommendSwitcher.isShouldFetchFromDb(ArgumentMatchers.anyLong())).thenReturn(false)
        val recommendFragmentViewModel = RecommendFragmentViewModel(limeRepository, loadingRecommendSwitcher)
        recommendFragmentViewModel.fetchRecommendMusics()
        assert(recommendFragmentViewModel.fetchRecommendTrigger.value
                == false)
    }
}