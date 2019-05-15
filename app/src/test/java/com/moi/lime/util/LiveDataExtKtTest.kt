package com.moi.lime.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moi.lime.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class LiveDataExtKtTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testDispatcher = TestCoroutineDispatcher()
    @ExperimentalCoroutinesApi
    val testScope = TestCoroutineScope(testDispatcher)


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun testDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testResourceLiveData() = testScope.runBlockingTest {
        val subject = resourceLiveData {
            delay(1000)
            "test"
        }
        subject.observeForTesting {
            assertEquals(Resource.loading(null), subject.value)
            advanceTimeBy(1000)
            assertEquals(Resource.success("test"), subject.value)

        }
    }
}