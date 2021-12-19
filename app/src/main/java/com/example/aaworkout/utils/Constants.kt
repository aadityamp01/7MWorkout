package com.example.aaworkout.utils
import com.example.aaworkout.models.ExerciseModel
import com.example.aaworkout.R
import java.util.*

class Constants {
    companion object{
        fun defaultExerciseList(): ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks =
                ExerciseModel(1, "Jumping Jacks", R.drawable.jumping_jacks,
                    isCompleted = false,
                    isSelected = false
                )
            exerciseList.add(jumpingJacks)

            val wallSit = ExerciseModel(2, "Wall Sit", R.drawable.wall_sit_lower_body,
                isCompleted = false,
                isSelected = false
            )
            exerciseList.add(wallSit)

            val pushUp = ExerciseModel(3, "Push Up", R.drawable.pushup_upper_body, false,
                isSelected = false
            )
            exerciseList.add(pushUp)

            val abdominalCrunch =
                ExerciseModel(4, "Abdominal Crunch", R.drawable.abdominal_crunch_core, false,
                    isSelected = false
                )
            exerciseList.add(abdominalCrunch)

            val stepUpOnChair =
                ExerciseModel(
                    5,
                    "Step-Up onto Chair",
                    R.drawable.stepup_onto_chair,
                    isCompleted = false,
                    isSelected = false
                )
            exerciseList.add(stepUpOnChair)

            val squat = ExerciseModel(6, "Squat", R.drawable.squat_lower_body, false, isSelected = false)
            exerciseList.add(squat)

            val tricepsDipOnChair =
                ExerciseModel(
                    7,
                    "Triceps Dip On Chair",
                    R.drawable.triceps_dip,
                    false,
                    isSelected = false
                )
            exerciseList.add(tricepsDipOnChair)

            val plank = ExerciseModel(8, "Plank", R.drawable.plank_core, false, isSelected = false)
            exerciseList.add(plank)

            val highKneesRunningInPlace =
                ExerciseModel(
                    9, "High Knees Running In Place",
                    R.drawable.high_knees_lower_body,
                    isCompleted = false,
                    isSelected = false
                )
            exerciseList.add(highKneesRunningInPlace)

            val lunges = ExerciseModel(10, "Lunges", R.drawable.lunge_lower_body,
                isCompleted = false,
                isSelected = false
            )
            exerciseList.add(lunges)

            val pushupAndRotation =
                ExerciseModel(
                    11,
                    "Push up and Rotation",
                    R.drawable.pushup_and_rotation,
                    isCompleted = false,
                    isSelected = false
                )
            exerciseList.add(pushupAndRotation)

            val sidePlank = ExerciseModel(12, "Side Plank", R.drawable.side_plank_core, false,
                isSelected = false
            )
            exerciseList.add(sidePlank)

            return exerciseList
        }
    }
}