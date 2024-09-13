package com.oguzhanozgokce.worldwords.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        val editor = sharedPreferences.edit()
        val json = gson.toJson(words)
        editor.putString(GET_WORD_KEY, json)
        editor.apply()
    }

    fun getWordsFromSharedPreferences(): List<Word> {
        val json = sharedPreferences.getString(GET_WORD_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Word>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
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

    private fun saveLearnedWords(words: List<Word>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(words)
        editor.putString(LEARNED_WORDS_KEY, json)
        editor.apply()
    }

     fun getLearnedWords(): List<Word> {
        val json = sharedPreferences.getString(LEARNED_WORDS_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Word>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
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

    private fun saveSavedWords(words: List<Word>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(words)
        editor.putString(SAVED_WORDS_KEY, json)
        editor.apply()
    }

    fun getSavedWords(): List<Word> {
        val json = sharedPreferences.getString(SAVED_WORDS_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Word>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
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
}
