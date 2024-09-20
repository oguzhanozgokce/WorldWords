package com.oguzhanozgokce.worldwords.ui.save

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oguzhanozgokce.worldwords.common.loadImage
import com.oguzhanozgokce.worldwords.databinding.ItemLayoutWordBinding
import com.oguzhanozgokce.worldwords.model.Word

class SaveAdapter(
    private val onItemClick: (Word) -> Unit,
    private val onMickClick: (Word) -> Unit
) : ListAdapter<Word, SaveAdapter.SaveViewHolder>(SaveDiffCallback()) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding =
            ItemLayoutWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SaveDiffCallback : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.english == newItem.english
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }
}
