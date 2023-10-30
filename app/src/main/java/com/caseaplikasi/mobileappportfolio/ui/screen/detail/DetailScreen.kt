package com.caseaplikasi.mobileappportfolio.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.caseaplikasi.mobileappportfolio.di.Injection
import com.caseaplikasi.mobileappportfolio.model.Transaction
import com.caseaplikasi.mobileappportfolio.ui.ViewModelFactory
import com.caseaplikasi.mobileappportfolio.ui.common.UiState
import com.caseaplikasi.mobileappportfolio.ui.components.TransaksiItem

@Composable
fun DetailScreen(
    label: String,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getListTransaction(label)
            }
            is UiState.Success -> {
                val listData = uiState.data.toList()
                val listTransaction = mutableListOf<Transaction>()

                for (map in listData) {
                    val trxDate = map["trx_date"] as? String
                    val nominal = (map["nominal"] as? Double)?.toInt()

                    if (trxDate != null && nominal != null) {
                        listTransaction.add(Transaction(trxDate, nominal))
                    }
                }
                TransactionRow(listTransaction = listTransaction, onBackClick =  navigateBack)

            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun TransactionRow(
    listTransaction: List<Transaction>,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column(modifier = modifier) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier.padding(16.dp).clickable { onBackClick() }
        )
        LazyColumn(
            modifier = modifier
        ){
            items(listTransaction) { transaction ->
                TransaksiItem(
                    transaction = transaction,
                )
            }
        }
    }
}