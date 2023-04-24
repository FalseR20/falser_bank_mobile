package com.falser.bank.ui.cards.pager


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.falser.bank.R
import com.falser.bank.ui.cards.new_card.NewCardActivity


class NewCardPageFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        val view = inflater.inflate(R.layout.fragment_page_card_new, container, false)
        view.setOnClickListener { _: View -> startNewCardActivity() }
        return view
    }

    private fun startNewCardActivity() {
        val intent = Intent(context, NewCardActivity::class.java)
        startActivity(intent)
    }

}