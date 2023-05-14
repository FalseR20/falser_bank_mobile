package com.falser.bank.ui.cards

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.falser.bank.databinding.FragmentCardsBinding
import com.falser.bank.ui.cards.pager.CardPagesAdapter
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
        binding.cardsPager.adapter = CardPagesAdapter(requireActivity())
        binding.cardsPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val isCardPageSelected = position != binding.cardsPager.adapter!!.itemCount - 1
                binding.transferButton.isEnabled = isCardPageSelected
                binding.depositButton.isEnabled = isCardPageSelected
            }
        })

        binding.transferButton.setOnClickListener {
            val intent = Intent(context, TransferActivity::class.java)
            intent.putExtra(
                "card",
                (binding.cardsPager.adapter!! as CardPagesAdapter).currentCard.id
            )
            startActivity(intent)
        }
    }

//    @SuppressLint("NotifyDataSetChanged")
//    override fun onStart() {
//        val adapter = binding.cardsPager.adapter!! as CardPagesAdapter
//        binding.cardsPager.post {
//            adapter.notifyDataSetChanged()
//            adapter.notifyItemChanged(adapter.cardsCount - 1)
//        }
//        super.onStart()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}