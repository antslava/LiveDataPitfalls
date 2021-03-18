package org.antmobile.livedata.case3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class UiState(
    val firstValue: String,
    val secondValue: String
)

class Case3ViewModel : ViewModel() {

    private val _uiState = MutableLiveData(UiState("Empty", "Empty"))
    val uiState: LiveData<UiState>
        get() = _uiState

    fun fetch() {
        _uiState.value = UiState("Empty", "Empty")
        fetchFirstValue()
        fetchSecondValue()
    }

    private fun fetchFirstValue() {
        viewModelScope.launch {
            val value = _uiState.value
            val result = simulateUseCase1()
            val updatedValue = value!!.copy(
                firstValue = result
            )
            _uiState.value = updatedValue
        }
    }

    private fun fetchSecondValue() {
        viewModelScope.launch {
            val value = _uiState.value
            val result = simulateUseCase2()
            val updatedValue = value!!.copy(
                secondValue = result
            )
            _uiState.value = updatedValue
        }
    }

    private suspend fun simulateUseCase1(): String {
        return withContext(Dispatchers.IO) {
            delay(100L)
            "First Value"
        }
    }

    private suspend fun simulateUseCase2(): String {
        return withContext(Dispatchers.IO) {
            delay(100L)
            "Second Value"
        }
    }

}
