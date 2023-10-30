package com.caseaplikasi.mobileappportfolio.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caseaplikasi.mobileappportfolio.data.PortfolioRepository
import com.caseaplikasi.mobileappportfolio.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Map<String, Any>>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Map<String, Any>>>>
        get() = _uiState

    fun getListTransaction(label:String) {
        viewModelScope.launch {
            repository.getListTransaction(label)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { chart ->
                    _uiState.value = UiState.Success(chart)
                }
        }
    }
}