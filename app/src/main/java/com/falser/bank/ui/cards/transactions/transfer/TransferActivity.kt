package com.falser.bank.ui.cards.transactions.transfer

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.falser.bank.databinding.ActivityTransferBinding

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.cancelButton.setOnClickListener { finish() }
        binding.sendButton.setOnClickListener { makeTransfer() }
        binding.value.filters = arrayOf(MoneyValueInputFilter(2))
    }

    private fun makeTransfer() {
        val value = binding.value.text.toString().replace(".", "").toInt()
        finish()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}