package com.example.aaworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aaworkout.databinding.ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_main.*  {this is deprecated now so we are using viewBinding instead}


// Check this vid for activity binding (Activities and fragments)  https://www.youtube.com/watch?v=omml4lK_b-A

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*        //We can also use binding deploy function if we don't want to use binding again and again

        binding.apply {
            llStart.setOnClickListener{
                Toast.makeText(this@MainActivity, "Start Workout here", Toast.LENGTH_SHORT).show()
            }
        }*/

        //Now just use binding.views_Id to import them from
        binding.llStart.setOnClickListener{
            Toast.makeText(this, "Start Workout here", Toast.LENGTH_SHORT).show()
        }

    }
}