package com.oguzhanozgokce.worldwords.ui.save

import android.view.LayoutInflater
import android.view.ViewGroup
import com.oguzhanozgokce.worldwords.common.base.BaseAdapter
import com.oguzhanozgokce.worldwords.common.base.BaseDiffCallback
import com.oguzhanozgokce.worldwords.common.base.BaseViewHolder
import com.oguzhanozgokce.worldwords.common.loadImage
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class SaveAdapter(
    private val onItemClick: (Word) -> Unit,
    private val onMicClick: (Word) -> Unit
) : BaseAdapter<Word, SaveAdapter.SaveViewHolder>(
    BaseDiffCallback(
        areItemsTheSameCallback = { oldItem, newItem -> oldItem.english == newItem.english },
        areContentsTheSameCallback = { oldItem, newItem -> oldItem == newItem }
    )
) {

    inner class SaveViewHolder(private val binding: ItemLayoutWordBinding) :
        BaseViewHolder<Word>(binding.root) {
        override fun bind(item: Word) {
            with(binding) {
                tvTurkishWord.text = item.turkish
                tvEnglishWord.text = item.english
                wordImage.loadImage(item.image)
                root.setOnClickListener {
                    onItemClick(item)
                }
                root.setOnLongClickListener {
                    onMicClick(item)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding = ItemLayoutWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaveViewHolder(binding)
    }
}
