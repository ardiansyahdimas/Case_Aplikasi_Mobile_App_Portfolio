package com.caseaplikasi.mobileappportfolio.data

import com.caseaplikasi.mobileappportfolio.model.ChartData
import com.caseaplikasi.mobileappportfolio.model.StaticDataSource
import com.caseaplikasi.mobileappportfolio.ui.common.Helper.getDataByLabel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PortfolioRepository {

    private val data = mutableListOf<ChartData>()

    init {
        if (data.isEmpty()) {
            StaticDataSource.chartData.forEach {
                data.add(it)
            }
        }
    }

    fun getPieChartData(): Flow<List<ChartData>> {
        return flowOf(data.filter { it.type == StaticDataSource.DONUT_CHART })
    }

    fun getLineChartData(): Flow<List<ChartData>> {
        return flowOf(data.filter { it.type == StaticDataSource.LINE_CHART })
    }

    fun getListTransaction(label:String): Flow<List<Map<String, Any>>> {
        return flowOf(getDataByLabel(label))
    }


    companion object {
        @Volatile
        private var instance: PortfolioRepository? = null

        fun getInstance(): PortfolioRepository =
            instance ?: synchronized(this) {
                PortfolioRepository().apply {
                    instance = this
                }
            }
    }
}