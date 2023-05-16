package com.falser.bank.ui.cards

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.falser.bank.databinding.FragmentCardsBinding
import com.falser.bank.ui.cards.new_card.NewCardActivity
import com.falser.bank.ui.cards.pager.CardPagesAdapter
import com.falser.bank.ui.cards.transactions.TransactionAdapter
import com.falser.bank.ui.cards.transactions.transfer.DepositActivity
import com.falser.bank.ui.cards.transactions.transfer.TransferActivity


class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
//    private lateinit var cardModel: CardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
//        cardModel = ViewModelProvider(this)[CardsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardsPager.adapter = CardPagesAdapter(requireActivity()) {
            startForResult.launch(Intent(context, NewCardActivity::class.java))
        }
        binding.cardsPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val isCardPageSelected = position != binding.cardsPager.adapter!!.itemCount - 1
                binding.transferButton.isEnabled = isCardPageSelected
                binding.depositButton.isEnabled = isCardPageSelected
            }
        })

        binding.depositButton.setOnClickListener {
            val intent = Intent(context, DepositActivity::class.java)
            intent.putExtra(
                "card", (binding.cardsPager.adapter!! as CardPagesAdapter).currentCard.id
            )
            startActivity(intent)
        }
        binding.transferButton.setOnClickListener {
            val intent = Intent(context, TransferActivity::class.java)
            intent.putExtra(
                "card", (binding.cardsPager.adapter!! as CardPagesAdapter).currentCard.id
            )
            startActivity(intent)
        }
        binding.transactions.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.transactions.adapter = TransactionAdapter()
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val adapter = binding.cardsPager.adapter!!
                adapter.notifyItemInserted(adapter.itemCount - 2)
                binding.cardsPager.currentItem = 0
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}