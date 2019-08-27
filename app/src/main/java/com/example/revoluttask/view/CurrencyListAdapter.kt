package com.example.revoluttask.view

import android.content.Context
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
        holder.bindView(context, items[position], changeListener, position == 0)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCurrency = view.img_currency
        val txtCurrency = view.txt_currency
        val edtCurrency = view.edt_currency
        val txtName = view.txt_currency_name
        val overlay = view.view_overlay

        fun bindView(context: Context, currency: Currency, changeListener: BaseChangeListener, isFirst: Boolean = false) {
            bindTextAndImage(context, currency)

            // bind header and rest of the list separate
            if (isFirst) bindFirst(currency, changeListener) else bindFromSecond(currency, changeListener)
        }

        // keep the first index of list separate, since that becomes the header
        private fun bindFirst(currency: Currency, changeListener: BaseChangeListener) {
            overlay.visibility = View.GONE
            edtCurrency.hint = null
            edtCurrency.setText(String.format("%.4f", currency.value))
            edtCurrency.selectAll()
            edtCurrency.requestFocus()

            edtCurrency.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(text: Editable?) {
                    try {
                        changeListener.onBaseInputChange(text.toString().toDouble())
                    } catch (e: Exception) {
                        // ignore number format exception
                    }
                }

                override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        }

        // keep the updating part of list separate
        private fun bindFromSecond(currency: Currency, changeListener: BaseChangeListener) {
            overlay.visibility = View.VISIBLE

            // clear the text to make hint visible
            edtCurrency.setText("")
            edtCurrency.hint = String.format("%.4f", currency.value)
            overlay.setOnClickListener(View.OnClickListener {
                changeListener.onBaseChange(currency)
            })
        }

        // independent of the edittext changes
        private fun bindTextAndImage(context: Context, currency: Currency) {
            txtCurrency.text = currency.name
            txtName.text = CountryNames.get(currency.name)

            imgCurrency.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    CountryFlags.get(currency.name)!!
                )
            )
        }
    }
}

interface BaseChangeListener {
    fun onBaseChange(currency: Currency)
    fun onBaseInputChange(value: Double)
}