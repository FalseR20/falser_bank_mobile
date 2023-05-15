package com.falser.bank.ui.cards.pager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.falser.bank.R


class NewCardPageFragment(private val onClickListener: OnClickListener) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_page_card_new, container, false)
        view.setOnClickListener(onClickListener)
        return view
    }
}