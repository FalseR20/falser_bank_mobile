package com.falser.bank.ui.cards

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.falser.bank.R
import com.falser.bank.databinding.FragmentCardsBinding
import com.falser.bank.ui.cards.pager.CardPagesAdapter
import com.falser.bank.ui.cards.transactions.transfer.TransferActivity


class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!
    private var paresAdapter: CardPagesAdapter? = null
    private var viewPager: ViewPager2? = null
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
        paresAdapter = activity?.let { CardPagesAdapter(it) }
        viewPager = view.findViewById(R.id.cards_pager)
        viewPager?.adapter = paresAdapter

        val transferButton = view.findViewById<Button>(R.id.transfer_button)
        transferButton.setOnClickListener {
            startActivity(Intent(context, TransferActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}