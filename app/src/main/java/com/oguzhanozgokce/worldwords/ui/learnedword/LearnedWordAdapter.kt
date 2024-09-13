package com.oguzhanozgokce.worldwords.ui.learnedword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.common.loadImage
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutLearnedWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class LearnedWordAdapter(
    private var words: List<Word>,
    private val onMicClick : (Word) -> Unit,
    private val onDeleteClick : (Word) -> Unit
) : RecyclerView.Adapter<LearnedWordAdapter.WordViewHolder>() {

    inner class WordViewHolder(private val binding: ItemLayoutLearnedWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            with(binding){
                tvTurkishWord.text = word.turkish
                tvEnglishWord.text = word.english
                wordImage.loadImage(word.image)
                root.setOnLongClickListener {
                    onMicClick(word)
                    true
                }
                icRemove.setOnClickListener {
                    onDeleteClick(word)
                }
            }
        }
    }

    fun updateWords(newWords: List<Word>) {
        words = newWords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding =
            ItemLayoutLearnedWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount(): Int = words.size
}
