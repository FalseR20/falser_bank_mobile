package com.falser.bank.ui.cards.new_card

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.falser.bank.R
import com.falser.bank.databinding.ActivityNewCardBinding
import com.falser.bank.repository.DatabaseHelper
import com.falser.bank.repository.DatabaseHelperFactory
import com.falser.bank.repository.models.Account
import com.falser.bank.repository.models.Card
import com.google.android.material.textfield.TextInputLayout


class NewCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCardBinding

    private lateinit var helper: DatabaseHelper

    private lateinit var systems: Array<String>
    private lateinit var serviceTimes: Array<String>
    private lateinit var accounts: Array<String>
    private lateinit var currencies: Array<String>

    private lateinit var systemFieldLayout: TextInputLayout
    private lateinit var systemField: AutoCompleteTextView

    private lateinit var serviceTimeFieldLayout: TextInputLayout
    private lateinit var serviceTimeField: AutoCompleteTextView

    private lateinit var cardholderNameFieldLayout: TextInputLayout
    private lateinit var cardholderNameField: EditText

    private lateinit var accountFieldLayout: TextInputLayout
    private lateinit var accountField: AutoCompleteTextView
    private lateinit var accountsMap: Map<String, Long>

    private lateinit var currenciesFieldLayout: TextInputLayout
    private lateinit var currenciesField: AutoCompleteTextView
    private lateinit var currenciesMap: Map<String, Long>


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        helper = DatabaseHelperFactory.helper

        fillArrays()
        fillFields()
        accountField.addTextChangedListener(afterTextChanged = {
            currenciesFieldLayout.isVisible = it.toString() == accounts[accounts.size - 1]
        })
        currenciesFieldLayout.isVisible = accounts.size == 1
        findViewById<Button>(R.id.cancel_button)!!.setOnClickListener { finish() }
        findViewById<Button>(R.id.create_button)!!.setOnClickListener { createCard() }
    }

    private fun fillArrays() {
        systems = resources.getStringArray(R.array.systems)
        serviceTimes = resources.getStringArray(R.array.service_times)

        val accountsModels = helper.accountDao.queryForAll()
        accounts = accountsModels.map {
            helper.currencyDao.refresh(it.currency)
            "Account ${it.id} with ${it.currency!!.format(it.balance!!)}"
        }.toTypedArray()
        accountsMap = accounts.zip(accountsModels.map { it.id!! }).toMap()
        accounts += "Create new account"

        val currenciesModels = helper.currencyDao.queryForAll()
        currencies = currenciesModels.map { it.code!! }.toTypedArray()
        currenciesMap = currencies.zip(currenciesModels.map { it.id!! }).toMap()
    }

    private fun fillFields() {

        // System field
        systemFieldLayout = findViewById(R.id.system_field_layout)
        systemField = findViewById(R.id.system_field)
        systemField.setAdapter(ArrayAdapter(this, R.layout.list_item, systems))

        // Service time field
        serviceTimeFieldLayout = findViewById(R.id.service_time_field_layout)
        serviceTimeField = findViewById(R.id.service_time_field)
        serviceTimeField.setAdapter(ArrayAdapter(this, R.layout.list_item, serviceTimes))
        serviceTimeField.setText(serviceTimes[2], false)

        // Cardholder name field
        cardholderNameFieldLayout = findViewById(R.id.cardholder_name_field_layout)
        cardholderNameField = findViewById(R.id.cardholder_name_field)

        // Account field
        accountFieldLayout = findViewById(R.id.account_field_layout)
        accountField = findViewById(R.id.account_field)
        accountField.setAdapter(ArrayAdapter(this, R.layout.list_item, accounts))
        accountField.setText(accounts[0], false)

        // Currencies field
        currenciesFieldLayout = findViewById(R.id.currency_field_layout)
        currenciesField = findViewById(R.id.currency_field)
        currenciesField.setAdapter(ArrayAdapter(this, R.layout.list_item, currencies))
    }

    private fun createCard() {
        if (!validate()) {
            Log.i(javaClass.simpleName, "Card parameters are invalid")
            return
        }
        Log.i(javaClass.simpleName, "Card parameters are valid")
        val account: Account
        if (accountField.editableText.toString() == accounts[accounts.size - 1]) {
            val id = currenciesMap.getValue(currenciesField.text.toString())
            account = Account(helper.currencyDao.queryForId(id), 0) // TODO: create new data
            helper.accountDao.create(account)
            Log.i(javaClass.simpleName, "Account is created: $account")
        } else {
            val id = accountsMap.getValue(accountField.text.toString())
            account = helper.accountDao.queryForId(id)
        }
        val card = Card(
            account,
            cardholderNameField.text.toString(),
            serviceTimeField.text.toString().toInt()
        )
        helper.cardDao.create(card)
        Log.i(javaClass.simpleName, "Card is created: $card")
        finish()
    }

    private fun validate(): Boolean {
        var isValid = true
        val errorText: String by lazy { isValid = false; getString(R.string.field_is_required) }
        systemFieldLayout.error = if (systemField.text.isEmpty()) errorText else null
        serviceTimeFieldLayout.error = if (serviceTimeField.text.isEmpty()) errorText else null
        cardholderNameFieldLayout.error =
            if (cardholderNameField.text.isBlank()) errorText else null
        accountFieldLayout.error = if (accountField.text.isEmpty()) errorText else null
        currenciesFieldLayout.error =
            if (accountField.editableText.toString() == accounts[accounts.size - 1] && currenciesField.text.isEmpty()) errorText else null
        return isValid
    }

}