package com.example.revoluttask.data.repository

import com.example.revoluttask.data.model.CurrencyRateResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRateService {

    companion object Factory {
        fun create(): CurrencyRateService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://revolut.duckdns.org/")
                .build()

            return retrofit.create(CurrencyRateService::class.java)
        }
    }

    @GET("latest")
    fun getCurrencyRates(@Query("base") query: String): Observable<CurrencyRateResponse>

}