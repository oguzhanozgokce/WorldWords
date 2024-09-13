package com.oguzhanozgokce.worldwords.ui.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordGameBinding
import com.oguzhanozgokce.worldwords.model.Word

class WordGameAdapter(
    private var words: List<Word>,
    private val onWordClicked: (Word) -> Unit
) : RecyclerView.Adapter<WordGameAdapter.WordViewHolder>() {

    private val selectedPositions = mutableSetOf<Int>()

    inner class WordViewHolder(private val binding: ItemLayoutWordGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(word: Word, showEnglish: Boolean, isSelected: Boolean) {
            if (showEnglish) {
                with(binding){
                    tvEnglishWord.text = word.english
                    tvEnglishWord.visibility = View.VISIBLE
                    tvTurkishWord.visibility = View.GONE
                }
            } else {
                with(binding){
                    tvTurkishWord.text = word.turkish
                    tvTurkishWord.visibility = View.VISIBLE
                    tvEnglishWord.visibility = View.GONE
                }
            }

            if (isSelected) {
                binding.root.setBackgroundResource(R.drawable.card_border)
            } else {
                binding.root.setBackgroundResource(R.drawable.card_normal)
            }

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (selectedPositions.contains(position)) {
                    selectedPositions.remove(position)
                } else {
                    selectedPositions.add(position)
                }
                notifyItemChanged(position)
                onWordClicked(word)
            }
        }
    }

    fun updateWords(newWords: List<Word>) {
        words = newWords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding =
            ItemLayoutWordGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        val showEnglish = position < words.size / 2
        val isSelected = selectedPositions.contains(position)
        holder.bind(word, showEnglish, isSelected)
    }

    override fun getItemCount(): Int = words.size
}

