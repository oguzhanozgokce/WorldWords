package com.oguzhanozgokce.worldwords.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.oguzhanozgokce.worldwords.common.getList
import com.oguzhanozgokce.worldwords.common.saveList
import com.oguzhanozgokce.worldwords.model.Word
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {

    companion object {
        private const val GET_WORD_KEY = "get_word_key"
        private const val LEARNED_WORDS_KEY = "learned_words_key"
        private const val SAVED_WORDS_KEY = "saved_words_key"
        private const val FIRST_RUN_KEY = "first_run_key"
    }

     fun saveWordsToSharedPreferences(words: List<Word>) {
         sharedPreferences.saveList(GET_WORD_KEY, gson, words)
    }

    fun getWordsFromSharedPreferences(): List<Word> {
        return sharedPreferences.getList(GET_WORD_KEY, gson)
    }

    fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(FIRST_RUN_KEY, true)
    }

    fun setFirstRunCompleted() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(FIRST_RUN_KEY, false)
        editor.apply()
    }

    fun addWordToSharedPreferences(word: Word) {
        val currentWords = getWordsFromSharedPreferences().toMutableList()
        currentWords.add(word)
        saveWordsToSharedPreferences(currentWords)
    }

    fun removeWordFromSharedPreferences(word: Word) {
        val currentWords = getWordsFromSharedPreferences().toMutableList()
        currentWords.remove(word)
        saveWordsToSharedPreferences(currentWords)
    }

    fun shuffleWordsAndSave(): List<Word> {
        val words = getWordsFromSharedPreferences().toMutableList()
        val shuffledWords = words.shuffled()
        saveWordsToSharedPreferences(shuffledWords)
        return shuffledWords
    }

    fun searchWords(query: String): List<Word> {
        val words = getWordsFromSharedPreferences()
        return if (query.isEmpty()) {
            words
        } else {
            words.filter { word ->
                word.english.contains(query, ignoreCase = true) || word.turkish.contains(
                    query,
                    ignoreCase = true
                )
            }
        }
    }

    private fun saveLearnedWords(words: List<Word>) {
        sharedPreferences.saveList(LEARNED_WORDS_KEY, gson, words)
    }

    fun getLearnedWords(): List<Word> {
         return sharedPreferences.getList(LEARNED_WORDS_KEY, gson)
    }

    fun addLearnedWord(word: Word) {
        val currentWords = getLearnedWords().toMutableList()
        currentWords.add(word)
        saveLearnedWords(currentWords)
    }

    fun removeLearnedWord(word: Word) {
        val currentWords = getLearnedWords().toMutableList()
        currentWords.remove(word)
        saveLearnedWords(currentWords)
    }

    fun isWordInLearnedList(word: Word): Boolean {
        val learnedWords = getLearnedWords()
        return learnedWords.contains(word)
    }

    fun isLearningListEmpty(): Boolean {
        val learnedWords = getLearnedWords()
        return learnedWords.isEmpty()
    }

    private fun saveSavedWords(words: List<Word>) {
        sharedPreferences.saveList(SAVED_WORDS_KEY, gson, words)
    }

    fun getSavedWords(): List<Word> {
        return sharedPreferences.getList(SAVED_WORDS_KEY, gson)
    }

    fun addSavedWord(word: Word) {
        val currentWords = getSavedWords().toMutableList()
        currentWords.add(word)
        saveSavedWords(currentWords)
    }

    fun removeSavedWord(word: Word) {
        val currentWords = getSavedWords().toMutableList()
        currentWords.remove(word)
        saveSavedWords(currentWords)
    }

    fun isWordInSavedList(word: Word): Boolean {
        val savedWords = getSavedWords()
        return savedWords.contains(word)
    }

    fun isSavedListEmpty(): Boolean {
        val savedWords = getSavedWords()
        return savedWords.isEmpty()
    }
}
