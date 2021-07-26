package com.example.aaworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aaworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHistoryActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarHistoryActivity.title = "BMI CALCULATOR"

        binding.toolbarHistoryActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}