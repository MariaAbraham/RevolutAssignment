package com.example.revoluttask.view

import android.content.Context
import android.opengl.Visibility
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttask.R
import com.example.revoluttask.data.model.Currency
import com.example.revoluttask.util.CountryFlags
import com.example.revoluttask.util.CountryNames
import kotlinx.android.synthetic.main.item_list.view.*


class CurrencyListAdapter(
    private val context: Context,
    private var items: ArrayList<Currency>,
    private var changeListener: BaseChangeListener
) :
    RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.edtCurrency.isEnabled = position == 0

        // update the base currency value only once,
        if (position == 0) {
            populateBaseCurrency(holder)
            return
        }

        holder.bindViews(context, items[position], changeListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun populateBaseCurrency(holder: ViewHolder) {
        holder.overlay.visibility = View.GONE
        holder.edtCurrency.setText(String.format("%.4f", items[0].value))
        holder.txtName.text = CountryNames.get(items[0].name)
        holder.edtCurrency.setSelection(holder.edtCurrency.text.length)
        holder.edtCurrency.selectAll()
        holder.txtCurrency.text = items[0].name
        holder.imgCurrency.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                CountryFlags.get(items[0].name)!!
            )
        )

        holder.edtCurrency.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                try {
                    var tempCurrency: Currency = items[0]
                    tempCurrency.value = text.toString().toDouble()
                    changeListener.onBaseChange(tempCurrency)
                } catch (e: Exception) {
                    // ignore number format exception
                }
            }

            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCurrency = view.img_currency
        val txtCurrency = view.txt_currency
        val edtCurrency = view.edt_currency
        val txtName = view.txt_currency_name
        val overlay = view.view_overlay

        fun bindViews(context: Context, currency: Currency, updateListener: BaseChangeListener) {
            txtCurrency.text = currency.name
            txtName.text = CountryNames.get(currency.name)

            if (!edtCurrency.isEnabled) {
                overlay.visibility = View.VISIBLE
                edtCurrency.hint = String.format("%.4f", currency.value)
            }

            imgCurrency.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    CountryFlags.get(currency.name)!!
                )
            )

            overlay.setOnClickListener(View.OnClickListener {
                updateListener.onBaseChange(currency)
            })
        }
    }
}

interface BaseChangeListener {
    fun onBaseChange(currency: Currency)
}