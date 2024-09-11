package com.oguzhanozgokce.worldwords.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.oguzhanozgokce.worldwords.databinding.FragmentWordGameBinding
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordGameFragment : Fragment() {
    private var _binding: FragmentWordGameBinding? = null
    private val binding get() = _binding!!
    private val wordGameViewModel: WordGameViewModel by viewModels()
    private lateinit var wordGameAdapter: WordGameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeWordPairs()
    }


    private fun setupRecyclerView() {
        wordGameAdapter = WordGameAdapter(emptyList()) { clickedWord ->
            Toast.makeText(requireContext(), "Clicked: ${clickedWord.english}", Toast.LENGTH_SHORT).show()
        }

        val gridLayoutManager = GridLayoutManager(requireContext(), 2) // 2 sütunlu grid
        binding.rwWordGame.layoutManager = gridLayoutManager
        binding.rwWordGame.adapter = wordGameAdapter
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}