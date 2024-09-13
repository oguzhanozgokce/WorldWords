package com.oguzhanozgokce.worldwords.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.oguzhanozgokce.worldwords.common.BaseFragment
import com.oguzhanozgokce.worldwords.common.showToast
import com.oguzhanozgokce.worldwords.databinding.FragmentWordGameBinding
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordGameFragment : BaseFragment<FragmentWordGameBinding>(FragmentWordGameBinding::inflate) {

    private val wordGameViewModel: WordGameViewModel by viewModels()
    private lateinit var wordGameAdapter: WordGameAdapter

    override fun FragmentWordGameBinding.bind() {
        setupRecyclerView()
        observeWordPairs()
    }

    private fun FragmentWordGameBinding.setupRecyclerView() {
        wordGameAdapter = WordGameAdapter(emptyList()) { clickedWord ->
            requireContext().showToast("Clicked: ${clickedWord.english}")
        }
        rwWordGame.adapter = wordGameAdapter
    }

    private fun observeWordPairs() {
        viewLifecycleOwner.lifecycleScope.launch {
            wordGameViewModel.wordPairs.collect { wordPairs ->
                val englishWords = wordPairs.map { it.first }.shuffled().toMutableList()
                val turkishWords = wordPairs.map { it.second }.shuffled().toMutableList()
                val mixedWords = mutableListOf<Word>()
                mixedWords.addAll(englishWords)
                mixedWords.addAll(turkishWords)
                wordGameAdapter.updateWords(mixedWords)
            }
        }
    }
}