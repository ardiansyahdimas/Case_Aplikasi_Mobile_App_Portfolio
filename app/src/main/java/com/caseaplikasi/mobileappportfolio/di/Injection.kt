package com.caseaplikasi.mobileappportfolio.di

import com.caseaplikasi.mobileappportfolio.data.PortfolioRepository

object Injection {
    fun provideRepository(): PortfolioRepository {
        return PortfolioRepository.getInstance()
    }
}