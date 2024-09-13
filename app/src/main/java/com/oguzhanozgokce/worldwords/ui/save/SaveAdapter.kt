package com.oguzhanozgokce.worldwords.ui.save

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class SaveAdapter(
    private var words: List<Word>,
    private val onItemClick: (Word) -> Unit,
) : RecyclerView.Adapter<SaveAdapter.SaveViewHolder>() {

    inner class SaveViewHolder(private val binding: ItemLayoutWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            with(binding) {
                tvTurkishWord.text = word.turkish
                tvEnglishWord.text = word.english
                Glide.with(wordImage)
                    .load(word.image)
                    .placeholder(R.drawable.ic_words)
                    .error(R.drawable.ic_bag)
                    .into(wordImage)
                root.setOnClickListener {
                    onItemClick(word)
                }
            }
        }
    }

    fun updateWords(newWords: List<Word>) {
        words = newWords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding =
            ItemLayoutWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount(): Int {
        return words.size
    }
}