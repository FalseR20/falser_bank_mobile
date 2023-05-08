package com.falser.bank.ui.cards.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.falser.bank.repository.DatabaseHelperFactory

class CardPagesAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>().apply {
        DatabaseHelperFactory.helper.cardDao.forEach { add(CardPageFragment(it)) }
        add(NewCardPageFragment())
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}