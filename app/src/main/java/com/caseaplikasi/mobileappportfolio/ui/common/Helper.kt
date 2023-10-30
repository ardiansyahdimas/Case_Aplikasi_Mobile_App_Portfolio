package com.caseaplikasi.mobileappportfolio.ui.common

import androidx.compose.ui.graphics.Color
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.piechart.models.PieChartData
import com.caseaplikasi.mobileappportfolio.model.ChartData
import com.caseaplikasi.mobileappportfolio.model.DonutChartEntry
import com.caseaplikasi.mobileappportfolio.model.StaticDataSource
import com.caseaplikasi.mobileappportfolio.model.Transaction
import com.google.gson.internal.LinkedTreeMap
import kotlin.random.Random

object Helper {
    fun getPieChartData(donutChartData: ChartData): PieChartData {
        val slices = mutableListOf<PieChartData.Slice>()

        val donutEntries = (donutChartData.data as List<LinkedTreeMap<String, Any>>).map {
            DonutChartEntry(
                it["label"] as String,
                (it["percentage"] as String).toFloat(), // Correct the type conversion
                (it["data"] as List<LinkedTreeMap<String, Any>>).map { entry ->
                    Transaction(
                        entry["trx_date"] as String,
                        (entry["nominal"] as Double).toInt() // Correct the type conversion
                    )
                }
            )
        }
        val random = Random(System.currentTimeMillis()) // Initialize a random number generator
        for (entry in donutEntries) {
            val color = Color(
                red = random.nextFloat(),
                green = random.nextFloat(),
                blue = random.nextFloat()
            )
            slices.add(
                PieChartData.Slice(
                    entry.label,
                    entry.percentage,
                    color
                )
            )
        }

        return PieChartData(
            slices = slices,
            plotType = PlotType.Pie
        )
    }

    fun getLineChartData(lineChartData: ChartData):List<Point>{
        val monthData = lineChartData.data as? Map<*, *>
        val monthList = monthData?.get("month") as? List<Int> ?: emptyList()
        val listSize = monthList.size
        val maxRange = monthList.max().toInt()
        return DataUtils.getLineChartData(listSize, start = 1, maxRange = maxRange)
    }

    fun getDataByLabel(label: String): List<Map<String, Any>> {
        val jsonData = StaticDataSource.jsonData
        val gson = StaticDataSource.gson

        try {
            val chartData = gson.fromJson(jsonData, Array<ChartData>::class.java).toList()

            for (data in chartData) {
                if (data.type == StaticDataSource.DONUT_CHART) {
                    val donutChartData = data.data as List<Map<String, Any>>

                    for (donutData in donutChartData) {
                        if (donutData["label"] == label) {
                            return donutData["data"] as List<Map<String, Any>>
                        }
                    }
                }
            }
        } catch (e: Exception) {
            // Handle parsing errors
            e.printStackTrace()
        }

        return emptyList()
    }
}