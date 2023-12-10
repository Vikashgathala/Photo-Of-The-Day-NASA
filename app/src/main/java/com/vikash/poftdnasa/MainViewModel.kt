package com.vikash.poftdnasa

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val _stateHolder = mutableStateOf(DataState())
    val stateHolderExposer: State<DataState> = _stateHolder

    init {
        fetchDataHandler()
    }

    fun fetchDataHandler() {
        viewModelScope.launch {
            try {
                val dataHandlerList = ApiService.getDataList(
                    "iJrkzhbq9lxerXK2COFKqtamTbahs6BHfBd7lfsn",
                    20
                )
                _stateHolder.value = _stateHolder.value.copy(
                    isLoading = false,
                    localDataList = dataHandlerList,
                )
            } catch (e: Exception) {
                _stateHolder.value = _stateHolder.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    data class DataState(
        val isLoading: Boolean = true,
        val localDataList: List<DataHandler> = emptyList(),
        val error: String? = null
    )
}
