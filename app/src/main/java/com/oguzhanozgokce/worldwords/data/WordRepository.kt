package com.oguzhanozgokce.worldwords.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    @ApplicationContext private val context: Context) {

    fun saveJsonDataToSharedPrefsIfFirstTime() {
        val isFirstRun = sharedPreferencesDataSource.isFirstRun()
        if (isFirstRun){
            val json = context.assets.open("word.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Word>>() {}.type
            val wordList : List<Word> = Gson().fromJson(json, type)
            sharedPreferencesDataSource.saveWordsToSharedPreferences(wordList)
            sharedPreferencesDataSource.setFirstRunCompleted()
        }
    }

    fun getWords(): List<Word> {
        return sharedPreferencesDataSource.getWordsFromSharedPreferences()
    }

    fun addWord(word: Word) {
        sharedPreferencesDataSource.addWordToSharedPreferences(word)
    }

    fun removeWord(word: Word) {
        sharedPreferencesDataSource.removeWordFromSharedPreferences(word)
    }

    fun shuffleWords(): List<Word> {
        return sharedPreferencesDataSource.shuffleWordsAndSave()
    }

    fun searchWords(query: String): List<Word> {
        return sharedPreferencesDataSource.searchWords(query)
    }

    fun getUsageExamples(word: Word): List<String> {
        return word.usageEnglish
    }

    fun getUsageTurkishExamples(word: Word): List<String>? {
        return word.usageTurkish
    }

    fun getLearnedWords(): List<Word> {
        return sharedPreferencesDataSource.getLearnedWords()
    }

    fun addWordToLearnedList(word: Word) {
        sharedPreferencesDataSource.addLearnedWord(word)
    }

    fun removeWordFromLearnedList(word: Word) {
        sharedPreferencesDataSource.removeLearnedWord(word)
    }

    fun isWordInLearnedList(word: Word): Boolean {
        return sharedPreferencesDataSource.isWordInLearnedList(word)
    }

    fun isLearningListEmpty(): Boolean {
        return sharedPreferencesDataSource.isLearningListEmpty()
    }

    fun getSavedWords(): List<Word> {
        return sharedPreferencesDataSource.getSavedWords()
    }

    fun addSavedWord(word: Word) {
        sharedPreferencesDataSource.addSavedWord(word)
    }

    fun removeSavedWord(word: Word) {
        sharedPreferencesDataSource.removeSavedWord(word)
    }

    fun isWordSaved(word: Word): Boolean {
        return sharedPreferencesDataSource.isWordInSavedList(word)
    }

    fun isSavedListEmpty(): Boolean {
        return sharedPreferencesDataSource.isSavedListEmpty()
    }

    fun addCustomWord(turkish: String, english: String, difficulty: Int, imageUrl: String, usageEnglish: List<String>, usageTurkish: List<String>) {
        val newWord = Word(turkish, english, difficulty, imageUrl, usageEnglish, usageTurkish)
        sharedPreferencesDataSource.addWordToSharedPreferences(newWord)
    }
}