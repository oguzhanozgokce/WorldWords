package com.oguzhanozgokce.worldwords.ui.word

import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class WordViewHolder(private val binding: ItemLayoutWordBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(word: Word, onItemClick: (Word) -> Unit, onMicClick: (Word) -> Unit) {
        binding.tvTurkishWord.text = word.turkish
        binding.tvEnglishWord.text = word.english
        binding.wordImage.setImageResource(word.image)
        binding.root.setOnClickListener { onItemClick(word) }
        binding.root.setOnLongClickListener {
            onMicClick(word)
             true
        }
    }
}
