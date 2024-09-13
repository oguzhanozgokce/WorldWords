package com.oguzhanozgokce.worldwords.ui.word

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class WordViewHolder(private val binding: ItemLayoutWordBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(word: Word, onItemClick: (Word) -> Unit, onMicClick: (Word) -> Unit) {
        with(binding) {
            tvTurkishWord.text = word.turkish
            tvEnglishWord.text = word.english
            Glide.with(wordImage.context)
                .load(word.image)
                .placeholder(R.drawable.ic_words)
                .error(R.drawable.ic_bag)
                .into(wordImage)
            root.setOnClickListener { onItemClick(word) }
            root.setOnLongClickListener {
                onMicClick(word)
                true
            }
        }
    }
}
