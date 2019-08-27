package com.example.revoluttask.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyRateResponse(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: HashMap<String, Double>
)