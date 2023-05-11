package com.falser.bank.ui.cards.transactions.transfer

import android.text.InputFilter
import android.text.Spanned

class MoneyValueInputFilter(digitsAfterZero: Int) : InputFilter {
    private val pattern: String = "^\\d+(\\.\\d{0,$digitsAfterZero})?"
    private val regex: Regex = Regex(pattern)

    override fun filter(
        source: CharSequence?,
        start: Int, end: Int,
        dest: Spanned?,
        dstart: Int, dend: Int,
    ): CharSequence? {
        val string = dest.toString() + source.toString()
        return if (regex.matches(string)) null else ""
    }
}
