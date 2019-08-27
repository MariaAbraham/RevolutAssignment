package com.example.revoluttask.util

object CountryNames {

    private val countryMap: HashMap<String, String> = HashMap()

    init {
        countryMap["AUD"] = "Australian Dollar"
        countryMap["BGN"] = "Bulgarian lev"
        countryMap["BRL"] = "Brazilian real"
        countryMap["CAD"] = "Canadian dollar"
        countryMap["CHF"] = "Swiss franc"
        countryMap["CNY"] = "Renminbi"
        countryMap["CZK"] = "Czech koruna"
        countryMap["DKK"] = "Danish krone"

        countryMap["GBP"] = "Get British Pound"
        countryMap["HKD"] = "Hong Kong dollar"
        countryMap["HRK"] = "Croatian kuna"
        countryMap["HUF"] = "Hungarian forint"
        countryMap["IDR"] = "Indonesian rupiah"
        countryMap["ILS"] = "Israeli new shekel"
        countryMap["INR"] = "Indian rupee"
        countryMap["ISK"] = "Icelandic króna"

        countryMap["JPY"] = "Japanese yen"
        countryMap["KRW"] = "South Korean won"
        countryMap["MXN"] = "Mexican peso"
        countryMap["MYR"] = "Malaysian ringgit"
        countryMap["NOK"] = "Norwegian krone"
        countryMap["NZD"] = "New Zealand dollar"
        countryMap["PHP"] = "Philippine peso"
        countryMap["PLN"] = "Polish złoty"

        countryMap["RON"] = "Romanian leu"
        countryMap["RUB"] = "Russian ruble"
        countryMap["SEK"] = "Swedish krona"
        countryMap["SGD"] = "Singapore dollar"
        countryMap["THB"] = "Thai baht"
        countryMap["TRY"] = "Turkish lira"
        countryMap["USD"] = "US Dollar"
        countryMap["ZAR"] = "South African rand"
        countryMap["EUR"] = "Euro"
    }

    fun get(code: String): String? {
        return CountryNames.countryMap[code]
    }

}