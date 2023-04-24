package com.falser.bank.ui.cards.new_card

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.falser.bank.databinding.ActivityNewCardBinding


class NewCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCardBinding


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityNewCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}