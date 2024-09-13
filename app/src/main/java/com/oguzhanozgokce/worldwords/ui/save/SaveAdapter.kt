package com.oguzhanozgokce.worldwords.ui.save

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.common.loadImage
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class SaveAdapter(
    private var words: List<Word>,
    private val onItemClick: (Word) -> Unit,
    private val onMickClick: (Word) -> Unit
) : RecyclerView.Adapter<SaveAdapter.SaveViewHolder>() {

    inner class SaveViewHolder(private val binding: ItemLayoutWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            with(binding) {
                tvTurkishWord.text = word.turkish
                tvEnglishWord.text = word.english
                wordImage.loadImage(word.image)
                root.setOnClickListener {
                    onItemClick(word)
                }
                root.setOnLongClickListener {
                    onMickClick(word)
                    true
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