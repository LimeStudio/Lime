package com.moi.lime.ui.home.recommend

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class RecommendFragmentViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val limeRepository = mock<LimeRepository>()

    @Test
    fun testCallRepo(){
         val recommendFragmentViewModel = RecommendFragmentViewModel(limeRepository)
        recommendFragmentViewModel.recommendResource.observeForever(mock())
        recommendFragmentViewModel.fetchRecommendTrigger.value = false
        verify(limeRepository).fetchRecommendMusics(ArgumentMatchers.anyBoolean())
    }
}