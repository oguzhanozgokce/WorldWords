package com.oguzhanozgokce.worldwords.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutExampleBinding

class ExamplesUseAdapter(
    private val onMicClick: (String) -> Unit
) : ListAdapter<String, ExamplesUseAdapter.ExampleViewHolder>(ExampleDiffCallback()) {

    inner class ExampleViewHolder(private val binding: ItemLayoutExampleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(example: String) {
            with(binding) {
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
        holder.bind(getItem(position))
    }

    class ExampleDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}

