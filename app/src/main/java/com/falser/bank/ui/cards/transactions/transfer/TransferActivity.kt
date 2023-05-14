package com.falser.bank.ui.cards.transactions.transfer

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.falser.bank.R
import com.falser.bank.databinding.ActivityTransferBinding
import com.falser.bank.repository.DatabaseHelperFactory
import com.falser.bank.repository.models.Account
import com.falser.bank.repository.models.Card
import com.falser.bank.repository.models.Currency
import com.falser.bank.repository.models.Transaction
import kotlin.math.pow

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding
    private lateinit var card: Card
    private lateinit var account: Account
    private lateinit var currency: Currency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = intent.extras!!
        card = DatabaseHelperFactory.helper.cardDao.queryForId(args.getLong("card"))
        account = card.account!!
        DatabaseHelperFactory.helper.accountDao.refresh(account)
        currency = account.currency!!
        DatabaseHelperFactory.helper.currencyDao.refresh(currency)

        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.cancelButton.setOnClickListener { finish() }
        binding.sendButton.setOnClickListener { makeTransfer() }
        binding.value.filters =
            arrayOf(MoneyValueInputFilter(currency.precision!!, account.balance!!))
        binding.valueLayout.helperText = "Max value: ${account.balanceHumanFormat()}"
    }

    private fun makeTransfer() {
        val value = floatStringToLong(binding.value.text.toString())
        if (value == 0L) {
            val errorText = getString(R.string.field_is_required)
            binding.valueLayout.error = errorText
            return
        }
        val transaction = Transaction(
            currency,
            account,
            value,
            binding.description.toString(),
        )
        DatabaseHelperFactory.helper.transactionDao.create(transaction)
        if (account.balance!! >= value) {
            account.balance = account.balance!! - value
            transaction.isSuccess = true
            DatabaseHelperFactory.helper.accountDao.update(account)
            DatabaseHelperFactory.helper.transactionDao.update(transaction)
        }
        finish()
    }

    private fun floatStringToLong(str: String): Long {
        val floatValue = str.toDoubleOrNull() ?: return 0
        return (10.0.pow(currency.precision!!) * floatValue).toLong()
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