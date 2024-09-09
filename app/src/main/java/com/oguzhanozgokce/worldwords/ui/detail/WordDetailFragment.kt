package com.oguzhanozgokce.worldwords.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanozgokce.worldwords.databinding.FragmentWordDetailBinding
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordDetailFragment : Fragment() {
    private var _binding: FragmentWordDetailBinding? = null
    private val binding get() = _binding!!
    private val wordDetailViewModel: WordDetailViewModel by viewModels()
    private lateinit var examplesAdapter: ExamplesUseAdapter
    private val args: WordDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedWord = args.word
        setupWordDetails(selectedWord)
        setupRecyclerView()
        observeUsageExamples(selectedWord)
        addWordToLearnedList(selectedWord)
    }

    private fun setupWordDetails(word: Word) {
        with(binding) {
            twSelectedTurkishWord.text = word.turkish
            twSelectedEnglishWord.text = word.english
            ivSelectedWord.setImageResource(word.image)
        }
    }

    private fun setupRecyclerView() {
        examplesAdapter = ExamplesUseAdapter(emptyList())
        binding.rwUsageExample.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = examplesAdapter
        }
    }

    private fun observeUsageExamples(word: Word) {
        wordDetailViewModel.loadUsageExamples(word)
        lifecycleScope.launch {
            wordDetailViewModel.usageExamples.collect { examples ->
                examplesAdapter = ExamplesUseAdapter(examples)
                binding.rwUsageExample.adapter = examplesAdapter
            }
        }
    }

    private fun addWordToLearnedList(word: Word) {
        binding.fab.setOnClickListener {
            wordDetailViewModel.addWordToLearnedList(word)
            Toast.makeText(requireContext(), "${word.english} added to learned list", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}