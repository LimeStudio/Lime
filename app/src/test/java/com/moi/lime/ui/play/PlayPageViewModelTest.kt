package com.moi.lime.ui.play

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.moi.lime.repository.LimeRepository
import com.moi.lime.util.*
import com.moi.lime.vo.MusicInformation
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class PlayPageViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val limeRepository = mock<LimeRepository>()

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    val testDispatchersRule = TestDispatchersRule()

    private val viewModel = PlayPageViewModel(limeRepository, MutableLiveData("573384240"), TestDispatchers(testDispatchersRule.testDispatcher))


    @ExperimentalCoroutinesApi
    @Before
    fun setup() = testDispatchersRule.testScope.runBlockingTest {

        Mockito.`when`(limeRepository.getAllMusicInformation()).thenReturn(getMusicInformationList())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testPlayPageDataListsWhenViewModelInit() = testDispatchersRule.testScope.runBlockingTest {

        viewModel.playPageDataLists.observeForTesting {
            assertEquals(2, viewModel.playPageDataLists.value?.size)
            assertEquals("573384240", viewModel.playPageDataLists.value?.firstOrNull()?.id)
            assertEquals("如约而至", viewModel.playPageDataLists.value?.firstOrNull()?.musicName)
            assertEquals("寻宝游戏", viewModel.playPageDataLists.value?.firstOrNull()?.musicAlbum)
            assertEquals("许嵩", viewModel.playPageDataLists.value?.firstOrNull()?.musicArtist)
            assertEquals("http://p1.music.126.net/BUFZLieG5a6E3ZVpkHP6fA==/109951163402069754.jpg", viewModel.playPageDataLists.value?.firstOrNull()?.musicImageUrl)
            assertEquals("http://m8.music.126.net/20200319143730/ea85427e4c09e89726c312f8b0bf85d1/ymusic/050f/1f08/81ee/c6326249cde554def94b604feac95cdc.flac", viewModel.playPageDataLists.value?.firstOrNull()?.musicUrl?.firstOrNull()?.url)
        }
        Mockito.verify(limeRepository).getAllMusicInformation()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testCurrentMusicInit() = testDispatchersRule.testScope.runBlockingTest {
        viewModel.currentMusic.observeForever(mock())
        assertEquals("573384240", viewModel.currentMusic.value?.id)
        viewModel.currentMusicIdChange(1)
        assertEquals("29850683", viewModel.currentMusic.value?.id)


    }


    @ExperimentalCoroutinesApi
    @Test
    fun testGetMusicImageUrlByPosition()=testDispatchersRule.testScope.runBlockingTest  {
        viewModel.playPageDataLists.observeForever(mock())
        val url = viewModel.getMusicImageUrlByPosition(1)
        assertEquals("http://p1.music.126.net/X0EDfXzxMQJiQ-71JFGdZw==/3238061746556733.jpg",url)
    }
    private fun getMusicInformationList(): List<MusicInformation> {
        return loadJsonFromFilePath("/json/MusicInfoList.json", javaClass).toList()
                ?: listOf()
    }

}