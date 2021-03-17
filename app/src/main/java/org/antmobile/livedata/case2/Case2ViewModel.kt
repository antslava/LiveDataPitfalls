package org.antmobile.livedata.case2

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

class Case2ViewModel : ViewModel() {

    private val _uiState = MutableLiveData(UiState("Empty", "Empty"))
    val uiState: LiveData<UiState>
        get() = _uiState

    fun fetch() {
        fetchFirstValue()
        fetchSecondValue()
    }

    private fun fetchFirstValue() {
        viewModelScope.launch {
            val result = simulateUseCase1()
            val updatedValue = _uiState.value!!.copy(
                firstValue = result
            )
            _uiState.postValue(updatedValue)
        }
    }

    private fun fetchSecondValue() {
        viewModelScope.launch {
            val result = simulateUseCase2()
            val updatedValue = _uiState.value!!.copy(
                secondValue = result
            )
            _uiState.postValue(updatedValue)
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
