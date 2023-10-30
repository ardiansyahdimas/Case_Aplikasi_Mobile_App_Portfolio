package com.caseaplikasi.mobileappportfolio.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DetailTransaksi : Screen("home/{label}") {
        fun createRoute(label: String) = "home/$label"
    }
}