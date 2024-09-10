package com.oguzhanozgokce.worldwords.ui.detail

import android.app.AlertDialog
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.FragmentWordDetailBinding
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class WordDetailFragment : Fragment() {
    private var _binding: FragmentWordDetailBinding? = null
    private val binding get() = _binding!!
    private val wordDetailViewModel: WordDetailViewModel by viewModels()
    private lateinit var examplesAdapter: ExamplesUseAdapter
    private val args: WordDetailFragmentArgs by navArgs()
    private lateinit var textToSpeech: TextToSpeech


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
        toggleSaveButton(selectedWord)

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

    private fun setupWordDetails(word: Word) {
        with(binding) {
            twSelectedTurkishWord.text = word.turkish.replaceFirstChar { it.uppercase() }
            twSelectedWord.text = word.english.replaceFirstChar { it.uppercase() }
            ivSelectedWord.setImageResource(word.image)
            binding.iwBack.setOnClickListener {
                findNavController().navigateUp()
            }
            binding.ivSelectedWord.setOnClickListener {
                toggleSaveButton(word)
            }
        }
    }

    private fun setupRecyclerView() {
        examplesAdapter = ExamplesUseAdapter(emptyList()) { example ->
            speakWord(example)
        }

        binding.rwUsageExample.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = examplesAdapter
        }
    }

    private fun speakWord(word: String) {
        textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun observeUsageExamples(word: Word) {
        wordDetailViewModel.loadUsageExamples(word)
        lifecycleScope.launch {
            wordDetailViewModel.usageExamples.collect { examples ->
                examplesAdapter = ExamplesUseAdapter(examples) { example ->
                    speakWord(example)
                }
                binding.rwUsageExample.adapter = examplesAdapter
            }
        }
    }

    private fun addWordToLearnedList(word: Word) {
        binding.fab.setOnClickListener {
            if (wordDetailViewModel.isWordInLearnedList(word)) {
                showWordAlreadyExistsDialog(word)
            } else {
                wordDetailViewModel.addWordToLearnedList(word)
                Toast.makeText(requireContext(), "${word.english} added to learned list", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showWordAlreadyExistsDialog(word: Word) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Already Exists")
            .setMessage("${word.english} is already in the learned list")
            .setPositiveButton("OK") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.custom_dialog_background)
        dialog.show()
    }

    private fun toggleSaveButton(word: Word) {
        updateSaveButtonState(word)

        binding.iwSaveWord.setOnClickListener {
            val isSaved = wordDetailViewModel.isWordInSavedList(word)

            if (isSaved) {
                wordDetailViewModel.removeWordFromSavedList(word)
                binding.iwSaveWord.setImageResource(R.drawable.ic_bookmark_add_24)
                Toast.makeText(requireContext(), "${word.english} removed from saved list", Toast.LENGTH_SHORT).show()
            } else {
                wordDetailViewModel.addWordToSavedList(word)
                binding.iwSaveWord.setImageResource(R.drawable.ic_bookmark_added_24)
                Toast.makeText(requireContext(), "${word.english} added to saved list", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateSaveButtonState(word: Word) {
        val isSaved = wordDetailViewModel.isWordInSavedList(word)
        if (isSaved) {
            binding.iwSaveWord.setImageResource(R.drawable.ic_bookmark_added_24)
        } else {
            binding.iwSaveWord.setImageResource(R.drawable.ic_bookmark_add_24)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        textToSpeech.shutdown()
    }
}