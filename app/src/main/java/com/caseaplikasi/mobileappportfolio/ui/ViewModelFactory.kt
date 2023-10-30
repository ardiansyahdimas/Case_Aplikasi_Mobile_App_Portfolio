package com.caseaplikasi.mobileappportfolio.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.caseaplikasi.mobileappportfolio.data.PortfolioRepository
import com.caseaplikasi.mobileappportfolio.ui.screen.detail.DetailViewModel
import com.caseaplikasi.mobileappportfolio.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: PortfolioRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}