package com.oguzhanozgokce.worldwords.ui.learnedword

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.oguzhanozgokce.worldwords.common.BaseFragment
import com.oguzhanozgokce.worldwords.common.gone
import com.oguzhanozgokce.worldwords.common.navigateTo
import com.oguzhanozgokce.worldwords.common.showCustomAlertDialog
import com.oguzhanozgokce.worldwords.common.visible
import com.oguzhanozgokce.worldwords.databinding.FragmentLearnedWordBinding
import com.oguzhanozgokce.worldwords.helper.TextToSpeechHelper
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LearnedWordFragment : BaseFragment<FragmentLearnedWordBinding>(FragmentLearnedWordBinding::inflate) {

    private val learnedWordViewModel: LearnedWordViewModel by viewModels()
    private lateinit var learnedWordAdapter: LearnedWordAdapter
    private lateinit var ttsHelper: TextToSpeechHelper

    override fun FragmentLearnedWordBinding.bind() {
        setupRecyclerView()
        observeLearnedWords()
        learnedWordViewModel.getLearnedWords()
        ttsHelper = TextToSpeechHelper(requireContext())
    }

    private fun FragmentLearnedWordBinding.setupRecyclerView() {
        learnedWordAdapter = LearnedWordAdapter(
            emptyList(),
            onMicClick = { word -> speakEnglishWord(word) },
            onDeleteClick = { word -> showDeleteConfirmationDialog(word) },
        )
        rwLearnedWord.adapter = learnedWordAdapter
    }

    private fun speakEnglishWord(word: Word) = ttsHelper.speak(word.english)

    private fun FragmentLearnedWordBinding.observeLearnedWords() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                learnedWordViewModel.learnedWords.collect { words ->
                    isView()
                    learnedWordAdapter.updateWords(words)
                }
            }
        }
    }

    private fun deleteWordFromLearnedList(word: Word) {
        learnedWordViewModel.deleteWord(word)
    }

    private fun showDeleteConfirmationDialog(word: Word) {
        showCustomAlertDialog(
            title = "Delete Word",
            message = "Are you sure you want to delete ${word.english} from the learned list?",
            positiveButtonText = "OK",
            onPositiveAction = { deleteWordFromLearnedList(word) },
            negativeButtonText = "Cancel"
        )
    }

    private fun FragmentLearnedWordBinding.showEmptyLearnedScreen() {
        emptyLayout.visible()
        rwLearnedWord.gone()
        emptyActionButton.setOnClickListener {
            navigateToWord()
        }
    }

    private fun FragmentLearnedWordBinding.hideEmptyLearnedScreen() {
        emptyLayout.gone()
        rwLearnedWord.visible()
    }

    private fun FragmentLearnedWordBinding.isView() {
        if (learnedWordViewModel.isLearningListEmpty()) {
            showEmptyLearnedScreen()
        } else {
            hideEmptyLearnedScreen()
        }
    }

    private fun navigateToWord(){
        val action = LearnedWordFragmentDirections.actionLearnedWordFragmentToWordFragment()
        navigateTo(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ttsHelper.shutdown()
    }
}
