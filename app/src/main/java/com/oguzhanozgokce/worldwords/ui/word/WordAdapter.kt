package com.oguzhanozgokce.worldwords.ui.word

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class WordAdapter(
    private var words: List<Word>,
    private val onItemClick: (Word) -> Unit) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    inner class WordViewHolder(private val binding: ItemLayoutWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.tvTurkishWord.text = word.turkish
            binding.tvEnglishWord.text = word.english
            binding.tvDifficulty.text = word.difficulty.toString()
            binding.wordImage.setImageResource(word.image)
            binding.root.setOnClickListener { onItemClick(word) }
        }
    }

    fun updateWords(newWords: List<Word>) {
        words = newWords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding =
            ItemLayoutWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount(): Int = words.size
}