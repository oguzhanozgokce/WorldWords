package com.oguzhanozgokce.worldwords.ui.word

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.FragmentWordBinding
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class WordFragment : Fragment() {
    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!
    private val wordViewModel: WordViewModel by viewModels()
    private lateinit var wordAdapter: WordAdapter
    private lateinit var textToSpeech: TextToSpeech


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSwipeToRefresh()
        observeWordList()

        textToSpeech = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = textToSpeech.setLanguage(Locale.ENGLISH)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TextToSpeech", "Language not supported")
                }
            } else {
                Log.e("TextToSpeech", "Initialization failed")
            }
        }

        binding.iwAddWord.setOnClickListener(){
            navigateToCustomWord()
        }

    }

    private fun setupRecyclerView() {
        wordAdapter = WordAdapter(
            emptyList(),
            onItemClick = { word -> navigateToWordDetail(word) },
            onMicClick = { word -> speakEnglishWord(word) }
        )

        binding.rwWord.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = wordAdapter
        }
    }

    private fun navigateToWordDetail(word: Word) {
        val action = WordFragmentDirections.actionWordFragmentToWordDetailFragment(word)
        findNavController().navigate(action)
    }

    private fun navigateToCustomWord() {
        val action = WordFragmentDirections.actionWordFragmentToCustomWordFragment()
        findNavController().navigate(action)
    }

    private fun speakEnglishWord(word: Word) {
        val englishWord = word.english
        textToSpeech.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun setupSwipeToRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            wordViewModel.shuffleWords() 
        }
    }

    private fun observeWordList() {
        viewLifecycleOwner.lifecycleScope.launch {
            wordViewModel.wordList.collect { words ->
                wordAdapter.updateWords(words)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}