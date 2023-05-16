package com.falser.bank.ui.cards.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.falser.bank.databinding.TransactionItemBinding
import com.falser.bank.repository.DatabaseHelperFactory

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    class TransactionHolder(val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TransactionItemBinding.inflate(inflater, parent, false)
        return TransactionHolder(binding)
    }

    override fun getItemCount(): Int {
        return DatabaseHelperFactory.helper.transactionDao.count()
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val transaction = DatabaseHelperFactory.helper.transactionDao.queryForId((position + 1).toLong())
        val currency = transaction.currency!!
        DatabaseHelperFactory.helper.currencyDao.refresh(currency)
        holder.binding.value.text = currency.format(transaction.value!!)
        holder.binding.card.text = "?"
        holder.binding.description.text = transaction.description!!.toString()
        holder.binding.timestamp.text = transaction.time.toString()
    }
}