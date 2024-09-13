package com.oguzhanozgokce.worldwords.ui.word

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.oguzhanozgokce.worldwords.common.BaseFragment
import com.oguzhanozgokce.worldwords.common.navigateTo
import com.oguzhanozgokce.worldwords.databinding.FragmentWordBinding
import com.oguzhanozgokce.worldwords.model.Word
import com.oguzhanozgokce.worldwords.helper.TextToSpeechHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordFragment : BaseFragment<FragmentWordBinding>(FragmentWordBinding::inflate)  {

    private val wordViewModel: WordViewModel by viewModels()
    private lateinit var wordAdapter: WordAdapter
    private lateinit var ttsHelper: TextToSpeechHelper

    override fun FragmentWordBinding.bind() {
        setupRecyclerView()
        setupSwipeToRefresh()
        observeWordList()
        wordViewModel.loadWords()
        ttsHelper = TextToSpeechHelper(requireContext())
        iwAddWord.setOnClickListener{
            navigateToCustomWord()
        }
    }

    private fun FragmentWordBinding.setupRecyclerView() {
        wordAdapter = WordAdapter(
            emptyList(),
            onItemClick = { word -> navigateToWordDetail(word) },
            onMicClick = { word -> speakEnglishWord(word) }
        )
        rwWord.adapter = wordAdapter
    }

    private fun navigateToWordDetail(word: Word) {
        val action = WordFragmentDirections.actionWordFragmentToWordDetailFragment(word)
        navigateTo(action)
    }

    private fun navigateToCustomWord() {
        val action = WordFragmentDirections.actionWordFragmentToCustomWordFragment()
        navigateTo(action)
    }

    private fun speakEnglishWord(word: Word) = ttsHelper.speak(word.english)

    private fun FragmentWordBinding.setupSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            wordViewModel.shuffleWords() 
        }
    }

    private fun FragmentWordBinding.observeWordList() {
        viewLifecycleOwner.lifecycleScope.launch {
            wordViewModel.wordList.collect { words ->
                wordAdapter.updateWords(words)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ttsHelper.shutdown()
    }
}