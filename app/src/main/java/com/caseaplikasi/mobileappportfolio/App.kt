package com.caseaplikasi.mobileappportfolio

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.caseaplikasi.mobileappportfolio.ui.navigation.Screen
import com.caseaplikasi.mobileappportfolio.ui.screen.detail.DetailScreen
import com.caseaplikasi.mobileappportfolio.ui.screen.home.HomeScreen

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { label ->
                        navController.navigate(Screen.DetailTransaksi.createRoute(label))
                    }
                )
            }
            composable(
                route = Screen.DetailTransaksi.route,
                arguments = listOf(navArgument("label") { type = NavType.StringType }),
            ) {
                val label = it.arguments?.getString("label") ?: ""
                DetailScreen(
                    label = label,
                    navigateBack = {
                        navController.navigateUp()
                    }
                    )
            }
        }
    }
}