package com.falser.bank.ui.cards.new_card

import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.falser.bank.R
import com.falser.bank.databinding.ActivityNewCardBinding
import com.google.android.material.textfield.TextInputLayout


class NewCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCardBinding
    private lateinit var systemField: TextInputLayout
    private lateinit var accountField: TextInputLayout
    private lateinit var currenciesField: TextInputLayout
    private lateinit var currenciesFieldView: AutoCompleteTextView

    private lateinit var systems: Array<String>
    private lateinit var accounts: Array<String>
    private lateinit var currencies: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        systems = resources.getStringArray(R.array.systems)
        accounts = arrayOf("new", "EXISTED...")
        currencies = resources.getStringArray(R.array.currencies)

        // System field
        systemField = findViewById(R.id.system_field)
        val systemsAdapter = ArrayAdapter(this, R.layout.list_item, systems)
        (systemField.editText!! as AutoCompleteTextView).setAdapter(systemsAdapter)

        // Account field
        accountField = findViewById(R.id.account_field)
        val accountsAdapter = ArrayAdapter(this, R.layout.list_item, accounts)
        val accountFieldView = accountField.editText as AutoCompleteTextView
        accountFieldView.setAdapter(accountsAdapter)


        // Currencies field
        currenciesField = findViewById(R.id.currency_field)
        val currenciesAdapter = ArrayAdapter(this, R.layout.list_item, currencies)
        currenciesFieldView = currenciesField.editText as AutoCompleteTextView
        currenciesFieldView.setAdapter(currenciesAdapter)

        accountFieldView.addTextChangedListener(afterTextChanged = ::newAccount)
    }

    private fun newAccount(text: Editable?) {
        if (text.toString() != accounts[0]) {
            currenciesFieldView.setText(currencies[0])
            currenciesField.isEnabled = false
        } else {
            currenciesFieldView.setText("")
            currenciesField.isEnabled = true
        }
    }
}