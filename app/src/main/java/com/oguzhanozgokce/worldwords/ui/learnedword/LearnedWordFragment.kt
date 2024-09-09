package com.oguzhanozgokce.worldwords.ui.learnedword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.oguzhanozgokce.worldwords.databinding.FragmentLearnedWordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LearnedWordFragment : Fragment() {
    private var _binding: FragmentLearnedWordBinding? = null
    private val binding get() = _binding!!
    private val learnedWordViewModel: LearnedWordViewModel by viewModels()
    private lateinit var learnedWordAdapter: LearnedWordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearnedWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeLearnedWords()
    }

    private fun setupRecyclerView() {
        learnedWordAdapter = LearnedWordAdapter(emptyList())
        binding.rwLearnedWord.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = learnedWordAdapter
        }
    }

    private fun observeLearnedWords() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                learnedWordViewModel.learnedWords.collect { words ->
                    learnedWordAdapter.updateWords(words)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
