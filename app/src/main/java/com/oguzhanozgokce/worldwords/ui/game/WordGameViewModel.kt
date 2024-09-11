package com.oguzhanozgokce.worldwords.ui.game

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.worldwords.data.WordRepository
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WordGameViewModel @Inject constructor(private val wordRepository: WordRepository) :
    ViewModel() {

    private val _wordPairs = MutableStateFlow<List<Pair<Word, Word>>>(emptyList())
    val wordPairs: StateFlow<List<Pair<Word, Word>>> get() = _wordPairs

    private val _selectedPositions = MutableStateFlow<Set<Int>>(emptySet())
    val selectedPositions: StateFlow<Set<Int>> get() = _selectedPositions

    private var isShuffled = false
    init {
        generateRandomWordPairs()
    }

    private fun generateRandomWordPairs() {
        if (!isShuffled) {
            val shuffledWords = wordRepository.shuffleWords().take(10)
            _wordPairs.value = shuffledWords.map { it to it.copy() }.shuffled()
            isShuffled = true
        }
    }

    fun toggleSelectedPosition(position: Int) {
        val updatedSet = _selectedPositions.value.toMutableSet()
        if (updatedSet.contains(position)) {
            updatedSet.remove(position)
        } else {
            updatedSet.add(position)
        }
        _selectedPositions.value = updatedSet
    }

    fun clearSelections() {
        _selectedPositions.value = emptySet()
    }
}