package com.oguzhanozgokce.worldwords.ui.word

import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.common.loadImage
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class WordViewHolder(private val binding: ItemLayoutWordBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(word: Word, onItemClick: (Word) -> Unit, onMicClick: (Word) -> Unit) {
        with(binding) {
            tvTurkishWord.text = word.turkish
            tvEnglishWord.text = word.english
            wordImage.loadImage(word.image)
            root.setOnClickListener { onItemClick(word) }
            root.setOnLongClickListener {
                onMicClick(word)
                true
            }
        }
    }
}
