package com.caseaplikasi.mobileappportfolio.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.caseaplikasi.mobileappportfolio.di.Injection
import com.caseaplikasi.mobileappportfolio.ui.ViewModelFactory
import com.caseaplikasi.mobileappportfolio.ui.common.Helper
import com.caseaplikasi.mobileappportfolio.ui.common.UiState
import com.caseaplikasi.mobileappportfolio.ui.components.LineChartView
import com.caseaplikasi.mobileappportfolio.ui.components.PieChartView

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        viewModel.pieUiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getPieChartData()
                }
                is UiState.Success -> {
                    val pieData = Helper.getPieChartData(uiState.data.first())
                    PieChartView(pieData,navigateToDetail)
                }
                is UiState.Error -> {}
            }
        }
        viewModel.lineUiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getLineChartData()
                }
                is UiState.Success -> {
                    val lineData = Helper.getLineChartData(uiState.data.first())
                    LineChartView(lineData)
                }
                is UiState.Error -> {}
            }
        }
    }
}