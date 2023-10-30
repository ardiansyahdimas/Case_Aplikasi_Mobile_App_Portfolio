package com.caseaplikasi.mobileappportfolio.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caseaplikasi.mobileappportfolio.data.PortfolioRepository
import com.caseaplikasi.mobileappportfolio.model.ChartData
import com.caseaplikasi.mobileappportfolio.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _lineUiState: MutableStateFlow<UiState<List<ChartData>>> = MutableStateFlow(UiState.Loading)
    val lineUiState: StateFlow<UiState<List<ChartData>>>
        get() = _lineUiState

    private val _pieUiState: MutableStateFlow<UiState<List<ChartData>>> = MutableStateFlow(UiState.Loading)
    val pieUiState: StateFlow<UiState<List<ChartData>>>
        get() = _pieUiState

    fun getLineChartData() {
        viewModelScope.launch {
            repository.getLineChartData()
                .catch {
                    _lineUiState.value = UiState.Error(it.message.toString())
                }
                .collect { chart ->
                    _lineUiState.value = UiState.Success(chart)
                }
        }
    }

    fun getPieChartData() {
        viewModelScope.launch {
            repository.getPieChartData()
                .catch {
                    _pieUiState.value = UiState.Error(it.message.toString())
                }
                .collect { chart ->
                    _pieUiState.value = UiState.Success(chart)
                }
        }
    }
}