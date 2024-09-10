package com.oguzhanozgokce.worldwords.ui.learnedword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutLearnedWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class LearnedWordAdapter(
    private var words: List<Word>,
    private val onMicClick : (Word) -> Unit,
    private val onDeleteClick : (Word) -> Unit
) : RecyclerView.Adapter<LearnedWordAdapter.WordViewHolder>() {

    inner class WordViewHolder(private val binding: ItemLayoutLearnedWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.tvTurkishWord.text = word.turkish
            binding.tvEnglishWord.text = word.english
            binding.wordImage.setImageResource(word.image)
            binding.root.setOnLongClickListener {
                onMicClick(word)
                true
            }
            binding.icRemove.setOnClickListener {
                onDeleteClick(word)
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
