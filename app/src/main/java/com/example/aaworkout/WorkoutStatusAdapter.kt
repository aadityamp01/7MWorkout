package com.example.aaworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aaworkout.databinding.ItemWokoutStatusBinding

/**
 * Very Important to learn here is that we are using View Binding in Adapter here
 * For ref -> https://www.youtube.com/watch?v=vmJFXmefBsM
 */

class WorkoutStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context): RecyclerView.Adapter<WorkoutStatusAdapter.ViewHolder>() {

    // Holds the TextView that will add each item
    class ViewHolder(val binding: ItemWokoutStatusBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWokoutStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]

        holder.binding.tvItem.text = model.getId().toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }


}