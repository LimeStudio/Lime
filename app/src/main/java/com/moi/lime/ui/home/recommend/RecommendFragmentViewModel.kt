package com.moi.lime.ui.home.recommend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.lime.testing.OpenForTesting
import com.moi.lime.core.livedata.SingleLiveEvent
import com.moi.lime.repository.LimeRepository
import javax.inject.Inject

@OpenForTesting
class RecommendFragmentViewModel @Inject constructor(private val limeRepository: LimeRepository, private val loadingRecommendSwitcher: LoadingRecommendSwitcher) : ViewModel() {
    val fetchRecommendTrigger = SingleLiveEvent<Boolean>()
    val recommendResource = fetchRecommendTrigger
            .switchMap {
                limeRepository.fetchRecommendMusics(it)
            }
            .map {
                loadingRecommendSwitcher.refreshState(it)
                it
            }

    init {
        fetchRecommendMusics()
    }


    fun fetchRecommendMusics() {
        fetchRecommendTrigger.value = loadingRecommendSwitcher.isShouldFetchFromDb(System.currentTimeMillis())
    }
}
