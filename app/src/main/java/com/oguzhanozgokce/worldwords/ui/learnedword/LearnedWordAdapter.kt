package com.oguzhanozgokce.worldwords.ui.learnedword

import android.view.LayoutInflater
import android.view.ViewGroup
import com.oguzhanozgokce.worldwords.common.base.BaseAdapter
import com.oguzhanozgokce.worldwords.common.base.BaseDiffCallback
import com.oguzhanozgokce.worldwords.common.base.BaseViewHolder
import com.oguzhanozgokce.worldwords.common.loadImage
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutLearnedWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class LearnedWordAdapter(
    private val onMicClick: (Word) -> Unit,
    private val onDeleteClick: (Word) -> Unit
) : BaseAdapter<Word, LearnedWordAdapter.WordViewHolder>(BaseDiffCallback(
    { oldItem, newItem -> oldItem.english == newItem.english },
    { oldItem, newItem -> oldItem == newItem }
)) {

    inner class WordViewHolder(private val binding: ItemLayoutLearnedWordBinding) :
        BaseViewHolder<Word>(binding.root) {
        override fun bind(item: Word) {
            with(binding) {
                tvTurkishWord.text = item.turkish
                tvEnglishWord.text = item.english
                wordImage.loadImage(item.image)
                root.setOnLongClickListener {
                    onMicClick(item)
                    true
                }
                icRemove.setOnClickListener {
                    onDeleteClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = ItemLayoutLearnedWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }
}


