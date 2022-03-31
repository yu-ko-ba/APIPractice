package com.example.apipractice.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.apipractice.domain.usecase.fetchwinelist.FetchWineListUseCase
import com.example.apipractice.domain.usecase.fetchwinelist.FetchWineListUseCaseResult
import com.example.apipractice.ui.uistate.SyncListUiState
import com.example.apipractice.ui.uistate.WineListItemUiState
import com.example.apipractice.ui.uistate.WineListItemUiStateConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val fetchWineListUseCase: FetchWineListUseCase) : ViewModel() {
    private val _syncListUiState = MutableStateFlow<SyncListUiState>(SyncListUiState.NotLoaded)
    val syncListUiState: StateFlow<SyncListUiState> = _syncListUiState

    private val _wineListItemUiState = MutableStateFlow<List<WineListItemUiState>>(mutableListOf())
    val wineListItemUiState: StateFlow<List<WineListItemUiState>> = _wineListItemUiState

    fun fetchWineList() {
        _syncListUiState.value = SyncListUiState.Loading
        viewModelScope.launch {
            val result = fetchWineListUseCase.invoke()
            if (result is FetchWineListUseCaseResult.Success) {
                _wineListItemUiState.value = WineListItemUiStateConverter().fromWineList(result.wineList)
                _syncListUiState.value = SyncListUiState.Success
            } else {
                _syncListUiState.value = SyncListUiState.Failure
            }
            _syncListUiState.value = SyncListUiState.NotLoaded
        }
    }

    class Factory(private val fetchWineListUseCase: FetchWineListUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass == MainViewModel::class.java) {
                MainViewModel(fetchWineListUseCase) as T
            } else {
                throw IllegalStateException()
            }
        }
    }
}