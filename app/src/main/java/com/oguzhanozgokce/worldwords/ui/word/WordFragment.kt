package com.oguzhanozgokce.worldwords.ui.word

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.oguzhanozgokce.worldwords.common.BaseFragment
import com.oguzhanozgokce.worldwords.common.addSimpleTextWatcher
import com.oguzhanozgokce.worldwords.common.navigateTo
import com.oguzhanozgokce.worldwords.common.setOnClearClickListener
import com.oguzhanozgokce.worldwords.common.updateSearchIconVisibility
import com.oguzhanozgokce.worldwords.databinding.FragmentWordBinding
import com.oguzhanozgokce.worldwords.helper.TextToSpeechHelper
import com.oguzhanozgokce.worldwords.model.Word
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
        wordViewModel.loadWords()
        observeWordList()
        setupSearch()
        hideKeyboard()
        ttsHelper = TextToSpeechHelper(requireContext())
        iwAddWord.setOnClickListener{
            navigateToCustomWord()
        }
    }

    private fun FragmentWordBinding.setupRecyclerView() {
        wordAdapter = WordAdapter(
            onItemClick = { word -> navigateToWordDetail(word) },
            onMicClick = { word -> speakEnglishWord(word) }
        )
        rwWord.apply {
            itemAnimator = null
            adapter = wordAdapter
        }
    }

    private fun FragmentWordBinding.observeWordList() {
        viewLifecycleOwner.lifecycleScope.launch {
            wordViewModel.filteredWords.collect { words ->
                val recyclerViewState = rwWord.layoutManager?.onSaveInstanceState()
                wordAdapter.submitList(words) {
                    rwWord.layoutManager?.onRestoreInstanceState(recyclerViewState)
                }
                swipeRefreshLayout.isRefreshing = false
            }
        }
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

    private fun FragmentWordBinding.setupSearch() {
        searchEditText.apply {
            addSimpleTextWatcher { text ->
                updateSearchIconVisibility(text)
                wordViewModel.searchWords(text.toString())
            }
            setOnClearClickListener {
                text.clear()
            }
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ttsHelper.shutdown()
    }
}