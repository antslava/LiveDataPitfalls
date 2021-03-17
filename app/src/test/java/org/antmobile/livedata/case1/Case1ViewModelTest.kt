package org.antmobile.livedata.case1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class Case1ViewModelTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Test
    fun tesT() = runBlockingTest {
        val case1ViewModel = Case1ViewModel(testDispatcher)

        case1ViewModel.uiState.test()
            .assertValue(UiState("Empty", "Empty"))

        case1ViewModel.fetch()
        testDispatcher.advanceUntilIdle()

        case1ViewModel.uiState.test()
            .assertValue(UiState("First Value", "Second Value"))
    }


}
