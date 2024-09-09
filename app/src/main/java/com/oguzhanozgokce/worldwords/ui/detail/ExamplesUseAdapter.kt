package com.oguzhanozgokce.worldwords.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.R

class ExamplesUseAdapter(
    private val examples: List<String>
) : RecyclerView.Adapter<ExamplesUseAdapter.ExampleViewHolder>() {

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exampleTextView: TextView = itemView.findViewById(R.id.tv_usage_example)
        private val difficultyTextView: TextView = itemView.findViewById(R.id.tv_usage_difficulty)

        fun bind(example: String, position: Int) {
            exampleTextView.text = example
            difficultyTextView.text = (position + 1).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_example, parent, false)
        return ExampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(examples[position], position)
    }

    override fun getItemCount(): Int {
        return examples.size
    }
}
