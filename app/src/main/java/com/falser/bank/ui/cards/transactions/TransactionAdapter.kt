package com.falser.bank.ui.cards.transactions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.falser.bank.R
import com.falser.bank.databinding.TransactionItemBinding
import com.falser.bank.repository.DatabaseHelperFactory

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    class TransactionHolder(val binding: TransactionItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = TransactionItemBinding.inflate(inflater, parent, false)
        return TransactionHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return DatabaseHelperFactory.helper.transactionDao.count()
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction =
            DatabaseHelperFactory.helper.transactionDao.queryForId((position + 1).toLong())
        val currency = transaction.currency!!
        DatabaseHelperFactory.helper.currencyDao.refresh(currency)
        val card = transaction.card!!
        DatabaseHelperFactory.helper.cardDao.refresh(card)
        holder.binding.value.text = currency.format(transaction.value!!)
        holder.binding.card.text = card.humanNumber()
        holder.binding.description.text = transaction.description!!.toString()
        holder.binding.timestamp.text = transaction.time.toString()
        if (transaction.isSuccess == true) {
            holder.binding.successLogo.setImageDrawable(
                ContextCompat.getDrawable(
                    holder.context, R.drawable.outline_check_circle_outline_24
                )
            )
        }
    }
}