package com.falser.bank.ui.cards.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CardPagesAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val length = 2
    private var fragments: Array<CardPageFragment> =
        Array(length) { i: Int -> CardPageFragment(i == length - 1) }

    override fun getItemCount(): Int {
        return length
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}