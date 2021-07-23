package com.example.aaworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aaworkout.databinding.ActivityBmiBinding

class BMI : AppCompatActivity() {

    private lateinit var binding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarBmiActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarBmiActivity.title = "BMI CALCULATOR"

        binding.toolbarBmiActivity.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}