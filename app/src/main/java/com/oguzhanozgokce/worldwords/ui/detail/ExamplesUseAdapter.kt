package com.oguzhanozgokce.worldwords.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutExampleBinding
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutLearnedWordBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class ExamplesUseAdapter(
    private val examples: List<String>,
    private val onMicClick: (String) -> Unit
) : RecyclerView.Adapter<ExamplesUseAdapter.ExampleViewHolder>() {

    inner class ExampleViewHolder(private val binding: ItemLayoutExampleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(example: String) {
            with(binding){
                tvUsageExample.text = example
                ivMic.setOnClickListener {
                    onMicClick(example)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val binding = ItemLayoutExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(examples[position])
    }

    override fun getItemCount(): Int {
        return examples.size
    }
}
