package org.antmobile.livedata.case1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class UiState(
    val firstValue: String,
    val secondValue: String
)

class Case1ViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableLiveData(UiState("Empty", "Empty"))
    val uiState: LiveData<UiState>
        get() = _uiState

    fun fetch() {
        fetchFirstValue()
        fetchSecondValue()
    }

    private fun fetchFirstValue() {
        viewModelScope.launch(dispatcher) {
            val result = simulateUseCase1()
            val updatedValue = _uiState.value!!.copy(
                firstValue = result
            )
            _uiState.postValue(updatedValue)
        }
    }

    private fun fetchSecondValue() {
        viewModelScope.launch(dispatcher) {
            val result = simulateUseCase2()
            val updatedValue = _uiState.value!!.copy(
                secondValue = result
            )
            _uiState.postValue(updatedValue)
        }
    }

    private suspend fun simulateUseCase1(): String {
        delay(100L)
        return "First Value"
    }

    private suspend fun simulateUseCase2(): String {
        delay(100L)
        return "Second Value"
    }

}
