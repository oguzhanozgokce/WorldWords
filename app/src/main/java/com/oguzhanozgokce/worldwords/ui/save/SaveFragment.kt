package com.oguzhanozgokce.worldwords.ui.save

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.oguzhanozgokce.worldwords.common.BaseFragment
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

    private fun observeSavedWords() {
        viewLifecycleOwner.lifecycleScope.launch {
            saveViewModel.saveWordList.collect { savedWords ->
                saveAdapter.updateWords(savedWords)
            }
        }
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