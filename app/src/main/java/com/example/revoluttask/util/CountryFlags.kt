package com.example.revoluttask.util

import com.example.revoluttask.R

object CountryFlags {

    private val flagMap: HashMap<String, Int> = HashMap()

    init {
        flagMap.clear()
        flagMap["AUD"] = R.drawable.ic_australia
        flagMap["BGN"] = R.drawable.ic_bulgaria
        flagMap["BRL"] = R.drawable.ic_brazil
        flagMap["CAD"] = R.drawable.ic_canada
        flagMap["CHF"] = R.drawable.ic_switzerland
        flagMap["CNY"] = R.drawable.ic_china
        flagMap["CZK"] = R.drawable.ic_czech_republic
        flagMap["DKK"] = R.drawable.ic_denmark

        flagMap["GBP"] = R.drawable.ic_united_kingdom
        flagMap["HKD"] = R.drawable.ic_hong_kong
        flagMap["HRK"] = R.drawable.ic_croatia
        flagMap["HUF"] = R.drawable.ic_hungary
        flagMap["IDR"] = R.drawable.ic_indonesia
        flagMap["ILS"] = R.drawable.ic_israel
        flagMap["INR"] = R.drawable.ic_india
        flagMap["ISK"] = R.drawable.ic_iceland

        flagMap["JPY"] = R.drawable.ic_japan
        flagMap["KRW"] = R.drawable.ic_south_korea
        flagMap["MXN"] = R.drawable.ic_mexico
        flagMap["MYR"] = R.drawable.ic_malaysia
        flagMap["NOK"] = R.drawable.ic_norway
        flagMap["NZD"] = R.drawable.ic_new_zealand
        flagMap["PHP"] = R.drawable.ic_philippines
        flagMap["PLN"] = R.drawable.ic_poland

        flagMap["RON"] = R.drawable.ic_romania
        flagMap["RUB"] = R.drawable.ic_russia
        flagMap["SEK"] = R.drawable.ic_sweden
        flagMap["SGD"] = R.drawable.ic_singapore
        flagMap["THB"] = R.drawable.ic_thailand
        flagMap["TRY"] = R.drawable.ic_turkey
        flagMap["USD"] = R.drawable.ic_united_states
        flagMap["ZAR"] = R.drawable.ic_south_africa
        flagMap["EUR"] = R.drawable.ic_european_union
    }

    fun get(code: String): Int? {
        return flagMap[code]
    }

}