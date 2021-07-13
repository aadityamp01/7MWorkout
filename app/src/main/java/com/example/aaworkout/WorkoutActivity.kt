package com.example.aaworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.aaworkout.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? =
        null // Variable for Rest Timer and later on I will initialize it.
    private var restProgress =
        0 // Variable for timer progress. As initial the rest progress is set to 0. As we are about to start.

    private var exerciseTimer: CountDownTimer? = null // Variable for Exercise Timer and later on we will initialize it.
    private var exerciseProgress = 0 // Variable for exercise timer progress. As initial the exercise progress is set to 0. As we are about to start.

    private var exerciseList: ArrayList<ExerciseModel>? = null // initialize the list later.
    private var currentExercisePosition = -1 // Current Position of Exercise.


    private lateinit var binding: ActivityWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //View Binding for easy access of all the view of activities with null and type safety.
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarWorkoutActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarWorkoutActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()
    }

    public override fun onDestroy() {
        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        super.onDestroy()
    }

    /**
     * Function is used to set the progress of timer using the progress
     */
    private fun setRestProgressBar() {

        binding.progressBar.progress = restProgress // Sets the current progress to the specified value.

        /*
         * @param millisInFuture The number of millis in the future from the call
         *   to {#start()} until the countdown is done and {#onFinish()}
         *   is called.
         * @param countDownInterval The interval along the way to receive
         *   {#onTick(long)} callbacks.
         */
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++ // It is increased to ascending order
                binding.progressBar.progress = 10 - restProgress // Indicates progress bar progress
                binding.tvTimer.text =
                    (10 - restProgress).toString()  // Current progress is set to text view in terms of seconds.
            }

            override fun onFinish() {

                currentExercisePosition++
                setupExerciseView()
                Toast.makeText(this@WorkoutActivity, "Here we will add exercise", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun setupRestView(){
        if(restTimer!= null){
            restTimer!!.cancel()
            restProgress = 0
        }

        binding.tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition+1].toString()

        setRestProgressBar()
    }

    private fun setExerciseProgressBar() {

        binding.progressBarEx.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding.progressBarEx.progress = 30 - exerciseProgress
                binding.tvTimerEx.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {

                    Toast.makeText(
                        this@WorkoutActivity,
                        "Congratulations! You have completed the 7 minutes workout.",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }.start()
    }

    private fun setupExerciseView(){

        binding.llRestView.visibility = View.GONE
        binding.llExerciseView.visibility = View.VISIBLE


        if(exerciseTimer!= null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        setExerciseProgressBar()
    }

}