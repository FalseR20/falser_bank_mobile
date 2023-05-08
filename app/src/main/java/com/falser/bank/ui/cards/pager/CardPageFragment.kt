package com.falser.bank.ui.cards.pager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.falser.bank.R
import com.falser.bank.repository.DatabaseHelperFactory
import com.falser.bank.repository.models.Card


class CardPageFragment(private val card: Card) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val account = card.account!!
        DatabaseHelperFactory.helper.accountDao.refresh(account)
        val currency = account.currency!!
        DatabaseHelperFactory.helper.currencyDao.refresh(currency)

        val result: View = inflater.inflate(R.layout.fragment_page_card, container, false)
        result.findViewById<TextView>(R.id.card_name).text = card.number.toString()
        result.findViewById<TextView>(R.id.balance_text_view).text = account.balanceString()
        result.findViewById<TextView>(R.id.currency_text_view).text = currency.code
        return result
    }

}