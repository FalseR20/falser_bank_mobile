package com.falser.bank.ui.cards.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.falser.bank.repository.DatabaseHelperFactory
import com.falser.bank.repository.models.Card

class CardPagesAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private lateinit var card: Card
    val currentCard: Card get() = card

    val cardsCount: Int get() = DatabaseHelperFactory.helper.cardDao.count()

    override fun getItemCount(): Int {
        return cardsCount + 1
    }

    override fun createFragment(position: Int): Fragment {
        if (position == cardsCount) return NewCardPageFragment()
        card = DatabaseHelperFactory.helper.cardDao.queryForAll()[position]
        return CardPageFragment(card)
    }

    fun update() {

    }
}