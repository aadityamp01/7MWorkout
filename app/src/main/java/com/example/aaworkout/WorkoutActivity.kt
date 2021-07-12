package com.example.aaworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aaworkout.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View Binding for easy access of all the view of activities with null and type safety.
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarWorkoutActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarWorkoutActivity.setNavigationOnClickListener {
            onBackPressed()
        }


    }
}