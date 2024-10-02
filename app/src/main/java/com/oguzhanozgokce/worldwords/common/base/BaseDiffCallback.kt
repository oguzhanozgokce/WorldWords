package com.oguzhanozgokce.worldwords.common.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffCallback<T : Any>(
    private val areItemsTheSameCallback: (T, T) -> Boolean,
    private val areContentsTheSameCallback: (T, T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsTheSameCallback(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areContentsTheSameCallback(oldItem, newItem)
    }
}



