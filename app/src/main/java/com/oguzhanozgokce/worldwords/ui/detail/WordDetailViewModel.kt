package com.oguzhanozgokce.worldwords.ui.detail

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.worldwords.data.WordRepository
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _selectedWord = MutableStateFlow<Word?>(null)
    val selectedWord: StateFlow<Word?> get() = _selectedWord

    private val _learnedWordList = MutableStateFlow<List<Word>>(emptyList())
    val learnedWordList: StateFlow<List<Word>> get() = _learnedWordList

    private val _usageExamples = MutableStateFlow<List<String>>(emptyList())
    val usageExamples: StateFlow<List<String>> get() = _usageExamples

    fun loadWordDetails(word: Word) {
        _selectedWord.value = word
        _usageExamples.value = wordRepository.getUsageExamples(word) ?: emptyList()
    }

    fun loadUsageExamples(word: Word) {
        _usageExamples.value = wordRepository.getUsageExamples(word) ?: emptyList()
    }

    fun addWordToLearnedList(word: Word) {
        wordRepository.addWordToLearnedList(word)
        _learnedWordList.value = wordRepository.getLearnedWords()
    }

    fun isWordInLearnedList(word: Word): Boolean {
        return wordRepository.isWordInLearnedList(word)
    }
}
