package com.oguzhanozgokce.worldwords.ui.save

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.oguzhanozgokce.worldwords.common.BaseFragment
import com.oguzhanozgokce.worldwords.common.gone
import com.oguzhanozgokce.worldwords.common.navigateTo
import com.oguzhanozgokce.worldwords.common.visible
import com.oguzhanozgokce.worldwords.databinding.FragmentSaveBinding
import com.oguzhanozgokce.worldwords.helper.TextToSpeechHelper
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveFragment : BaseFragment<FragmentSaveBinding>(FragmentSaveBinding::inflate) {

    private val saveViewModel: SaveViewModel by viewModels()
    private lateinit var saveAdapter: SaveAdapter
    private lateinit var ttsHelper: TextToSpeechHelper

    override fun FragmentSaveBinding.bind() {
        setupRecyclerView()
        observeSavedWords()
        ttsHelper = TextToSpeechHelper(requireContext())
        saveViewModel.getSavedWords()
    }

    private fun FragmentSaveBinding.setupRecyclerView() {
        saveAdapter = SaveAdapter(
            emptyList(),
            onItemClick = { word -> navigateToWordDetail(word) },
            onMickClick = { word -> speakEnglishWord(word) }
        )
        rwSave.adapter = saveAdapter
    }

    private fun FragmentSaveBinding.observeSavedWords() {
        viewLifecycleOwner.lifecycleScope.launch {
            saveViewModel.saveWordList.collect { savedWords ->
                isView()
                saveAdapter.updateWords(savedWords)
            }
        }
    }

    private fun FragmentSaveBinding.showEmptyLearnedScreen() {
        emptyLayout.visible()
        rwSave.gone()
        emptyActionButton.setOnClickListener {
            navigateToWord()
        }
    }

    private fun FragmentSaveBinding.hideEmptyLearnedScreen() {
        emptyLayout.gone()
        rwSave.visible()
    }

    private fun FragmentSaveBinding.isView() {
        if (saveViewModel.isLearningListEmpty()) {
            showEmptyLearnedScreen()
        } else {
            hideEmptyLearnedScreen()
        }
    }

    private fun navigateToWord(){
        val action = SaveFragmentDirections.actionSaveFragmentToWordFragment()
        navigateTo(action)
    }

    private fun speakEnglishWord(word: Word) = ttsHelper.speak(word.english)

    private fun navigateToWordDetail(word: Word) {
        val action = SaveFragmentDirections.actionSaveFragmentToWordDetailFragment(word)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsHelper.shutdown()
    }
}