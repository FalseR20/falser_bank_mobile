package com.falser.bank.ui.cards.new_card

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.falser.bank.R
import com.falser.bank.databinding.ActivityNewCardBinding
import com.google.android.material.textfield.TextInputLayout


class NewCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCardBinding

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

    private lateinit var currenciesFieldLayout: TextInputLayout
    private lateinit var currenciesField: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fillArrays()
        fillFields()
        accountField.addTextChangedListener(afterTextChanged = ::newAccount)
        findViewById<Button>(R.id.cancel_button)!!.setOnClickListener { finish() }
        findViewById<Button>(R.id.create_button)!!.setOnClickListener { createCard() }
    }

    private fun validate(): Boolean {
        var isValid = true
        val errorText: String by lazy { isValid = false; getString(R.string.field_is_required) }
        systemFieldLayout.error = if (systemField.text.isEmpty()) errorText else null
        serviceTimeFieldLayout.error = if (serviceTimeField.text.isEmpty()) errorText else null
        cardholderNameFieldLayout.error =
            if (cardholderNameField.text.isBlank()) errorText else null
        accountFieldLayout.error = if (accountField.text.isEmpty()) errorText else null
        currenciesFieldLayout.error = if (currenciesField.text.isEmpty()) errorText else null
        return isValid
    }

    private fun createCard() {
        if (validate()) Log.i(javaClass.simpleName, "Card is valid")
    }

    private fun fillArrays() {
        systems = resources.getStringArray(R.array.systems)
        serviceTimes = resources.getStringArray(R.array.service_times)
        accounts = arrayOf("new", "EXISTED...")
        currencies = resources.getStringArray(R.array.currencies)
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

    private fun newAccount(text: Editable?) {
        if (text.toString() != accounts[0]) {
            currenciesField.setText(currencies[0])
            currenciesFieldLayout.isEnabled = false
        } else {
            currenciesField.setText("")
            currenciesFieldLayout.isEnabled = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // app icon in action bar clicked; go home
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}