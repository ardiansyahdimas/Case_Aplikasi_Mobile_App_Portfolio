package com.caseaplikasi.mobileappportfolio.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caseaplikasi.mobileappportfolio.model.Transaction
import com.caseaplikasi.mobileappportfolio.ui.theme.CaseAplikasiMobileAppPortfolioTheme

@Composable
fun TransaksiItem(
    transaction: Transaction,
    modifier: Modifier = Modifier
        .padding(10.dp)
) {
  Card(
      shape = RoundedCornerShape(8.dp),
      colors = CardDefaults.cardColors(
          containerColor = Color.Gray
      ),
      modifier = modifier

  ) {
      Column(
          modifier = Modifier
              .padding(10.dp)
              .fillMaxWidth()
      ) {
          Text(text =  "Transaction Date: ${transaction.trx_date}")
          Text(text = "Nominal: ${transaction.nominal}")
      }
  }
}

@Preview
@Composable
fun PrevTransaction() {
    CaseAplikasiMobileAppPortfolioTheme {
        TransaksiItem(transaction = Transaction("27-03-2023", 100000))
    }
}