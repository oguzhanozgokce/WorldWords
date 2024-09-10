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
        private const val LEARNED_WORDS_KEY = "learned_words_key"
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
}
