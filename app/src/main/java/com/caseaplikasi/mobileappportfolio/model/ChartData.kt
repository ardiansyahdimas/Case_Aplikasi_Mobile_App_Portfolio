package com.caseaplikasi.mobileappportfolio.model

data class ChartData(
    val type: String,
    val data: Any
)

data class DonutChartEntry(
    val label: String,
    val percentage: Float,
    val data: List<Transaction>
)

data class Transaction(
    val trx_date: String,
    val nominal: Int
)

data class LineChartData(
    val month: List<Double>
)
