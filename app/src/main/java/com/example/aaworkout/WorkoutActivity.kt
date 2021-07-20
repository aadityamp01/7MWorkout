package com.example.aaworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aaworkout.databinding.ActivityWorkoutBinding
import java.util.*
import kotlin.collections.ArrayList

class WorkoutActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var restTimer: CountDownTimer? =
        null // Variable for Rest Timer and later on I will initialize it.
    private var restProgress =
        0 // Variable for timer progress. As initial the rest progress is set to 0. As we are about to start.

    private var exerciseTimer: CountDownTimer? = null // Variable for Exercise Timer and later on we will initialize it.
    private var exerciseProgress = 0 // Variable for exercise timer progress. As initial the exercise progress is set to 0. As we are about to start.

    private var exerciseList: ArrayList<ExerciseModel>? = null // initialize the list later.
    private var currentExercisePosition = -1 // Current Position of Exercise.

    private var ttospeech: TextToSpeech? = null  //creating var for text to speech
    private var player: MediaPlayer? = null // Creating a var for Media Player to use later.

    private var exerciseAdapter: WorkoutStatusAdapter? = null

    private lateinit var binding: ActivityWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ttospeech = TextToSpeech(this@WorkoutActivity,this@WorkoutActivity)


        //View Binding for easy access of all the view of activities with null and type safety.
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarWorkoutActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarWorkoutActivity.setNavigationOnClickListener {
            onBackPressed()

            TODO("SET Action for the player, when back button pressed in between exercise; " +
                    "player keeps looping on main screen if pressed")
        }

        exerciseList = Constants.defaultExerciseList()

        setupRestView()    // REST View is set in this function

        // setting up the exercise recycler view
        setupExerciseStatusRecyclerView()

    }

    public override fun onDestroy() {
        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        if (ttospeech != null) {
            ttospeech!!.stop()
            ttospeech!!.shutdown()
        }

        if(player != null){
            player!!.stop()
        }

        super.onDestroy()
    }

    /**
     * This the TextToSpeech fun
     *
     * Called to signal the completion of the TextToSpeech engine initialization.
     */

    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS){
            val result = ttospeech!!.setLanguage(Locale.US)

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The language is not supported" )
            }
        }
        else{
            Log.e("TTA", "Initialization failed!")
        }
    }

    /**
     * Function is used to set the timer for REST.
     */

    private fun setupRestView(){

        /**
         * Here the sound file is added in to "raw" folder in resources.
         * And played using MediaPlayer. MediaPlayer class can be used to control playback
         * of audio/video files and streams.
         */
        try {
            val soundURI =
                Uri.parse("android.resource://com.example.aaworkout/" + R.raw.start_sound)
            player = MediaPlayer.create(applicationContext, soundURI)
            player!!.isLooping = false // Sets the player to be looping or non-looping.
            player!!.start() // Starts Playback.
        } catch (e: Exception) {
            e.printStackTrace()
        }


        binding.llRestView.visibility = View.VISIBLE
        binding.llExerciseView.visibility = View.GONE

        if(restTimer!= null){
            restTimer!!.cancel()
            restProgress = 0
        }

        binding.tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()

        setRestProgressBar()
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

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged() // Notified the current item to adapter class to reflect it into UI.


                setupExerciseView()
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

        binding.ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage()) // getImage is used to get image form exercise model
        binding.tvExerciseName.text = exerciseList!![currentExercisePosition].getName()

        speakOut(exerciseList!![currentExercisePosition].getName())

        setExerciseProgressBar()
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
                exerciseList!![currentExercisePosition].setIsSelected(false) // exercise is completed so selection is set to false
                exerciseList!![currentExercisePosition].setIsCompleted(true) // updating in the list that this exercise is completed
                exerciseAdapter!!.notifyDataSetChanged() // Notifying to adapter class.

                if(currentExercisePosition < exerciseList?.size!! - 1){
                    setupRestView()
                }else {

                    if(player != null){
                        player!!.stop()
                    }

                    Toast.makeText(
                        this@WorkoutActivity,
                        "Congratulations! You have completed the 7 minutes workout.",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this@WorkoutActivity, MainActivity::class.java)

                    startActivity(intent)
                }
            }
        } .start()
    }

    /**
     * Function is used to speak the text what we pass to it.
     */
    private fun speakOut(text: String) {
        ttospeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")   // We can use QUEUE_ADD
    }


    /**
     * Function is used to set up the recycler view to UI and assign the Layout Manager and Adapter Class is attached to it.
     */
    private fun setupExerciseStatusRecyclerView() {

        // Defining a layout manager to recycle view
        // Here we have used Linear Layout Manager with horizontal scroll.
        binding.rvWorkoutStatus.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // As the adapter expect the exercises list and context so initialize it passing it.
        exerciseAdapter = WorkoutStatusAdapter(exerciseList!!, this)

        // Adapter class is attached to recycler view
        binding.rvWorkoutStatus.adapter = exerciseAdapter
    }

}