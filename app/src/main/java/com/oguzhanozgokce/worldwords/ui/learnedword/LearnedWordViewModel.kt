package com.oguzhanozgokce.worldwords.ui.learnedword

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.worldwords.data.WordRepository
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LearnedWordViewModel @Inject constructor(private val wordRepository: WordRepository): ViewModel() {

    private val _learnedWords = MutableStateFlow<List<Word>>(emptyList())
    val learnedWords: StateFlow<List<Word>> get() = _learnedWords

    init {
        loadLearnedWords()
    }

    private fun loadLearnedWords() {
        _learnedWords.value = wordRepository.getLearnedWords()
    }

     fun deleteWord(word: Word) {
       wordRepository.removeWordFromLearnedList(word)
        _learnedWords.value = wordRepository.getLearnedWords()
    }
}