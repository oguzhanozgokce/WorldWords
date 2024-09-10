package com.oguzhanozgokce.worldwords.ui.save

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.worldwords.data.WordRepository
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val wordRepository: WordRepository) : ViewModel() {

    private val _saveWordList = MutableStateFlow<List<Word>>(emptyList())
    val saveWordList: StateFlow<List<Word>> get() = _saveWordList

     fun getSavedWords() {
        _saveWordList.value = wordRepository.getSavedWords()
    }
}