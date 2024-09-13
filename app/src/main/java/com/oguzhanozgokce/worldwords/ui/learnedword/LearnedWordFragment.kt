package com.oguzhanozgokce.worldwords.ui.learnedword

import android.app.AlertDialog
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.FragmentLearnedWordBinding
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class LearnedWordFragment : Fragment() {
    private var _binding: FragmentLearnedWordBinding? = null
    private val binding get() = _binding!!
    private val learnedWordViewModel: LearnedWordViewModel by viewModels()
    private lateinit var learnedWordAdapter: LearnedWordAdapter
    private lateinit var textToSpeech: TextToSpeech

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
        learnedWordViewModel.getLearnedWords()

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
    }

    private fun setupRecyclerView() {
        learnedWordAdapter = LearnedWordAdapter(
            emptyList(),
            onMicClick = { word -> speakEnglishWord(word) },
            onDeleteClick = { word -> showDeleteConfirmationDialog(word) },
        )
        binding.rwLearnedWord.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = learnedWordAdapter
        }
    }

    private fun speakEnglishWord(word: Word) {
        val englishWord = word.english
        textToSpeech.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, null)
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

    private fun deleteWordFromLearnedList(word: Word) {
        learnedWordViewModel.deleteWord(word)
    }

    private fun showDeleteConfirmationDialog(word: Word) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delete Word")
            .setMessage("Are you sure you want to delete ${word.english} from the learned list?")
            .setPositiveButton("OK") { dialogInterface, _ ->
                deleteWordFromLearnedList(word)
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()

        dialog.window?.setBackgroundDrawableResource(R.drawable.custom_dialog_background)
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        textToSpeech.shutdown()
        _binding = null
    }
}
