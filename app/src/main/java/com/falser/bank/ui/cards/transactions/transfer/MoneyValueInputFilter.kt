package com.falser.bank.ui.cards.transactions.transfer

import android.text.InputFilter
import android.text.Spanned
import kotlin.math.pow

class MoneyValueInputFilter(private val digitsAfterZero: Int, private val maxValue: Long) :
    InputFilter {
    private val pattern: String = "^\\d+(\\.\\d{0,$digitsAfterZero})?"
    private val regex: Regex = Regex(pattern)

    override fun filter(
        source: CharSequence?,
        start: Int, end: Int,
        dest: Spanned?,
        dstart: Int, dend: Int,
    ): CharSequence? {
        val string = dest.toString() + source.toString()
        if (!regex.matches(string))
            return ""
        if (floatStringToLong(string) > maxValue)
            return ""
        return null
    }

    private fun floatStringToLong(str: String): Long {
        val floatValue = str.toFloatOrNull() ?: return 0
        return (floatValue * 10.0.pow(digitsAfterZero)).toLong()
    }
}
