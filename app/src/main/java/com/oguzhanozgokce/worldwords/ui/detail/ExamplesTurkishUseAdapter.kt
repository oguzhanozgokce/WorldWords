package com.oguzhanozgokce.worldwords.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.databinding.ItemTurkishLayoutBinding

class ExamplesTurkishUseAdapter(
    private val examples: List<String>,
    private val onClickCard : (String) -> Unit
) : RecyclerView.Adapter<ExamplesTurkishUseAdapter.ExampleTurkishViewHolder>() {

    inner class ExampleTurkishViewHolder(private val binding: ItemTurkishLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(example: String) {
            with(binding){
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
        holder.bind(examples[position])
    }

    override fun getItemCount(): Int {
        return examples.size
    }
}