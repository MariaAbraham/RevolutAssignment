package com.example.revoluttask.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.revoluttask.R
import com.example.revoluttask.data.model.Currency
import com.example.revoluttask.data.repository.RepositoryProvider
import com.google.android.material.appbar.CollapsingToolbarLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), BaseChangeListener {

    private val rateRepository = RepositoryProvider.currencyRateRepo()
    private lateinit var disposable: Disposable

    private var currencyList: ArrayList<Currency> = ArrayList()
    private var ratesInEur: HashMap<String, Double> = HashMap()

    private lateinit var currencyListAdapter: CurrencyListAdapter
    private var baseCurrency: Currency = Currency("EUR", 1.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbar.title = "Rates"
        initializeAdapter()

        ratesInEur[baseCurrency.name] = baseCurrency.value
        syncCurrencyRates()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onBaseChange(currency: Currency) {
        updateBase(currency)
        moveToTop()
    }

    private fun initializeAdapter() {
        recyler_currency.layoutManager = LinearLayoutManager(this)
        recyler_currency.hasFixedSize()
        currencyListAdapter = CurrencyListAdapter(this, currencyList, this)
        recyler_currency.adapter = currencyListAdapter
    }

    private fun syncCurrencyRates() {
        disposable = Observable.interval(1, TimeUnit.SECONDS)
            .flatMap { rateRepository.getCurrencyRates("EUR") }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                ratesInEur.putAll(result.rates)
                updateList()
                updateBase(baseCurrency)
                if (progress.isShown) {
                    hideProgress()
                }
            }, { error ->
                error.printStackTrace()
            })
    }

    private fun updateBase(currency: Currency) {
        //  add previous one back to list and remove new base.
        val index: Int = currencyList.indexOf(currency)
        baseCurrency = currency
        currencyList.remove(currency)
        currencyList.add(0, currency)
        currencyListAdapter.notifyItemMoved(index, 0)
    }

    private fun moveToTop() {
        recyler_currency.scrollToPosition(0)
    }

    private fun updateList() {
        if (currencyList.isEmpty()) {
            for ((key, value) in ratesInEur) {
                currencyList.add(Currency(key, value))
            }
        }

        // Base is the current editing currency
        // and rate map has rates based on EUR.
        // Eg: 10 INR to AUD = 1/inrRateInEur * audRateInEur * 10
        var baseRate = 1.0 / ratesInEur[baseCurrency.name]!!
        var baseInput = baseCurrency.value

        for (item in currencyList) {
            if (item.name != baseCurrency.name) {
                var itemRate = ratesInEur[item.name]!!
                item.value = baseRate * itemRate * baseInput
            }
        }

        currencyListAdapter.notifyDataSetChanged()
    }

    fun hideProgress() {
        progress.visibility = View.GONE
    }
}