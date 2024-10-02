package com.oguzhanozgokce.worldwords.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.oguzhanozgokce.worldwords.common.base.BaseAdapter
import com.oguzhanozgokce.worldwords.common.base.BaseDiffCallback
import com.oguzhanozgokce.worldwords.common.base.BaseViewHolder
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutExampleBinding

class ExamplesUseAdapter(
    private val onMicClick: (String) -> Unit
) : BaseAdapter<String, ExamplesUseAdapter.ExampleViewHolder>(
    BaseDiffCallback(
        areItemsTheSameCallback = { oldItem, newItem -> oldItem == newItem },
        areContentsTheSameCallback = { oldItem, newItem -> oldItem == newItem }
    )
) {

    inner class ExampleViewHolder(private val binding: ItemLayoutExampleBinding) :
        BaseViewHolder<String>(binding.root) {
        override fun bind(item: String) {
            with(binding) {
                tvUsageExample.text = item
                ivMic.setOnClickListener {
                    onMicClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val binding = ItemLayoutExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleViewHolder(binding)
    }
}


