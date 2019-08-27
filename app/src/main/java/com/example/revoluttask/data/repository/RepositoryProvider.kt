package com.example.revoluttask.data.repository

object RepositoryProvider {

    fun currencyRateRepo(): CurrencyRateRepository {
        return CurrencyRateRepository(CurrencyRateService.create())
    }

}