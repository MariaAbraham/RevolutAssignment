package com.example.revoluttask.data.repository

import com.example.revoluttask.data.model.CurrencyRateResponse
import io.reactivex.Observable

class CurrencyRateRepository(private val service: CurrencyRateService) {

    fun getCurrencyRates(baseCurrency: String): Observable<CurrencyRateResponse> {
        return service.getCurrencyRates(query = baseCurrency)
    }

}