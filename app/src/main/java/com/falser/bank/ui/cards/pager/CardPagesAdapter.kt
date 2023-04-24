package com.falser.bank.ui.cards.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CardPagesAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val length = 2
    private val fragments = mutableListOf<Fragment>().apply {
        repeat(length - 1) { add(CardPageFragment()) }
        add(NewCardPageFragment())
    }


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}