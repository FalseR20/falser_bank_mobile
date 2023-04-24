package com.falser.bank.ui.cards.pager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.falser.bank.R


class CardPageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val result: View = inflater.inflate(R.layout.fragment_page_card, container, false)
        result.findViewById<TextView>(R.id.card_name).text = "1234 **** **** 0001"
        result.findViewById<TextView>(R.id.balance_text_view).text = "12 356.40"
        result.findViewById<TextView>(R.id.currency_text_view).text = "BYN"

        return result
    }

}