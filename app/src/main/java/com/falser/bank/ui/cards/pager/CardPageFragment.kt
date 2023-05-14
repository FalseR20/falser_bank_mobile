package com.falser.bank.ui.cards.pager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.falser.bank.databinding.FragmentPageCardBinding
import com.falser.bank.repository.DatabaseHelperFactory
import com.falser.bank.repository.models.Account
import com.falser.bank.repository.models.Card
import com.falser.bank.repository.models.Currency

class CardPageFragment(val card: Card) : Fragment() {
    private lateinit var binding: FragmentPageCardBinding
    private lateinit var account: Account
    private lateinit var currency: Currency

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPageCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        account = card.account!!
        DatabaseHelperFactory.helper.accountDao.refresh(account)
        currency = account.currency!!
        DatabaseHelperFactory.helper.currencyDao.refresh(currency)

        binding.cardName.setText(card.number.toString())
        binding.balanceTextView.text = account.balanceString()
        binding.currencyTextView.text = currency.code

        DatabaseHelperFactory.helper.accountDao.refresh(account)
        binding.balanceTextView.text = account.balanceString()
        super.onStart()
    }
}