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

    fun addCustomWord(turkish: String, english: String, difficulty: Int, imageUrl: String, usageEnglish: List<String>, usageTurkish: List<String>) {
        wordRepository.addCustomWord(turkish, english, difficulty, imageUrl, usageEnglish, usageTurkish)
        _customWordList.value = wordRepository.getWords()
    }
}