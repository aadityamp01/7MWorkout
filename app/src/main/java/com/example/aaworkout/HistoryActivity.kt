package com.example.aaworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aaworkout.adapters.HistoryAdapter
import com.example.aaworkout.database.SqliteOpenHelper
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
        getAllCompletedDates()

    }

    /**
     * Function is used to get the list exercise completed dates.
     */
    private fun getAllCompletedDates() {

        // Instance of the Sqlite Open Helper class.
        val dbHandler = SqliteOpenHelper(this, null)

        val allCompletedDatesList =
            dbHandler.getAllCompletedDatesList() // List of history table data

        //  We will pass that list to the adapter class which we have created and bind it to the recycler view.)
        if (allCompletedDatesList.size > 0) {
            // Here if the List size is greater then 0 we will display the item in the recycler view or else we will show the text view that no data is available.
            binding.tvHistory.visibility = View.VISIBLE
            binding.rvHistory.visibility = View.VISIBLE
            binding.tvNoDataAvailable.visibility = View.GONE

            // Creates a vertical Layout Manager
            binding.rvHistory.layoutManager = LinearLayoutManager(this)

            // History adapter is initialized and the list is passed in the param.
            val historyAdapter = HistoryAdapter(this, allCompletedDatesList)

            // Access the RecyclerView Adapter and load the data into it
            binding.rvHistory.adapter = historyAdapter
        } else {
            binding.tvHistory.visibility = View.GONE
            binding.rvHistory.visibility = View.GONE
            binding.tvNoDataAvailable.visibility = View.VISIBLE
        }
    }
}