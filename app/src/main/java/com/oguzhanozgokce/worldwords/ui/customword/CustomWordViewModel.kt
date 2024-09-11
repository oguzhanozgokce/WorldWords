package com.oguzhanozgokce.worldwords.ui.customword

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.worldwords.data.WordRepository
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CustomWordViewModel @Inject constructor(private val wordRepository: WordRepository) : ViewModel() {

    private val _customWordList = MutableStateFlow<List<Word>>(emptyList())
    val customWordList: StateFlow<List<Word>> get() = _customWordList

    fun addCustomWord(turkish: String, english: String, imageResId: Int, difficulty: Int = 1) {
        val customWord = Word(turkish, english, difficulty, imageResId)
        val updatedList = _customWordList.value.toMutableList()
        updatedList.add(customWord)
        _customWordList.value = updatedList
        wordRepository.addWord(customWord)
    }

}