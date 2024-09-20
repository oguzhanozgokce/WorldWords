package com.oguzhanozgokce.worldwords.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.databinding.ItemTurkishLayoutBinding

class ExamplesTurkishUseAdapter(
    private val onClickCard: (String) -> Unit
) : ListAdapter<String, ExamplesTurkishUseAdapter.ExampleTurkishViewHolder>(ExampleDiffCallback()) {

    inner class ExampleTurkishViewHolder(private val binding: ItemTurkishLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(example: String) {
            with(binding) {
                tvUsageExample.text = example
                itemView.setOnClickListener {
                    tvUsageExample.isInvisible = !tvUsageExample.isInvisible
                    onClickCard(example)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleTurkishViewHolder {
        val binding = ItemTurkishLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleTurkishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExampleTurkishViewHolder, position: Int) {
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
