package com.oguzhanozgokce.worldwords.ui.word

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.worldwords.data.WordRepository
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(private val wordRepository: WordRepository) : ViewModel()  {
    private val _wordList = MutableStateFlow<List<Word>>(emptyList())
    val wordList: StateFlow<List<Word>> get() = _wordList

    private val _filteredWords = MutableStateFlow<List<Word>>(emptyList())
    val filteredWords: StateFlow<List<Word>> get() = _filteredWords


    init {
        loadWords()
    }

    fun loadWords() {
        _wordList.value = wordRepository.getWords()
        _filteredWords.value = _wordList.value
    }

    fun shuffleWords() {
        _wordList.value = wordRepository.shuffleWords()
        _filteredWords.value = _wordList.value
    }

    fun searchWords(query: String) {
        _filteredWords.value = wordRepository.searchWords(query)
    }
}