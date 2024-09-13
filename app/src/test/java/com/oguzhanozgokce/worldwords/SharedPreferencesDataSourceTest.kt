package com.oguzhanozgokce.worldwords

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oguzhanozgokce.worldwords.data.SharedPreferencesDataSource
import com.oguzhanozgokce.worldwords.model.Word
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.mock
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SharedPreferencesDataSourceTest{
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var gson: Gson
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferencesDataSource: SharedPreferencesDataSource

    @Before
    fun setUp(){
        sharedPreferences = mock(SharedPreferences::class.java)
        gson = Gson()
        editor = mock(SharedPreferences.Editor::class.java)

        whenever(sharedPreferences.edit()).thenReturn(editor)
        whenever(editor.putString(anyString(), anyString())).thenReturn(editor)
        whenever(editor.putBoolean(anyString(), anyBoolean())).thenReturn(editor)

        sharedPreferencesDataSource = SharedPreferencesDataSource(sharedPreferences, gson)
    }

    @Test
    fun `saveWordsToSharedPreferences saves words correctly`() {
        val words = listOf(Word("elma", "apple", 2, ""), Word("muz", "banana",3,""))
        sharedPreferencesDataSource.saveWordsToSharedPreferences(words)
        verify(editor).putString(eq("get_word_key"), anyString())
        verify(editor).apply()
    }

    @Test
    fun `getWordsFromSharedPreferences returns correct word list`() {
        val wordList = listOf(Word("elma", "apple", 2, ""))
        val wordListJson = gson.toJson(wordList)
        whenever(sharedPreferences.getString(eq("get_word_key"), anyOrNull())).thenReturn(wordListJson)
        val result = sharedPreferencesDataSource.getWordsFromSharedPreferences()
        assertEquals(1, result.size)
        assertEquals("apple", result[0].english)
        assertEquals("elma", result[0].turkish)
    }

    @Test
    fun `isFirstRun returns true if it's the first run`() {
        whenever(sharedPreferences.getBoolean(eq("first_run_key"), eq(true))).thenReturn(true)
        val result = sharedPreferencesDataSource.isFirstRun()
        assertEquals(true, result)
    }

    @Test
    fun `isFirstRun returns false if it's not the first run`() {
        whenever(sharedPreferences.getBoolean(eq("first_run_key"), eq(true))).thenReturn(false)
        val result = sharedPreferencesDataSource.isFirstRun()
        assertEquals(false, result)
    }

    @Test
    fun `setFirstRunCompleted sets the first run flag to false`() {
        sharedPreferencesDataSource.setFirstRunCompleted()
        verify(editor).putBoolean(eq("first_run_key"), eq(false))
        verify(editor).apply()
    }

    @Test
    fun `addWordToSharedPreferences adds word to existing list`() {
        val existingWords = listOf(Word("elma", "apple", 1,""))
        val newWord = Word("portakal", "orange", 1,"")
        val json = gson.toJson(existingWords)
        whenever(sharedPreferences.getString(eq("get_word_key"), anyOrNull())).thenReturn(json)

        sharedPreferencesDataSource.addWordToSharedPreferences(newWord)
        verify(editor).putString(eq("get_word_key"), anyString())
        verify(editor).apply()
    }

    @Test
    fun `removeWordFromSharedPreferences removes word from existing list`() {
        val existingWords = listOf(Word("elma", "apple", 1,""))
        val wordToRemove = Word("elma", "apple", 1,"")
        val json = gson.toJson(existingWords)
        whenever(sharedPreferences.getString(eq("get_word_key"), anyOrNull())).thenReturn(json)
        sharedPreferencesDataSource.removeWordFromSharedPreferences(wordToRemove)
        verify(editor).putString(eq("get_word_key"), anyString())
        verify(editor).apply()
    }

    @Test
    fun `shuffleWordsAndSave returns shuffled word list`() {
        val wordList = mutableListOf(
            Word("elma", "apple", 1, ""),
            Word("muz", "banana", 1, ""),
            Word("armut", "pear", 1, ""),
            Word("portakal", "orange", 1, ""))
        val json = gson.toJson(wordList)
        whenever(sharedPreferences.getString(eq("get_word_key"), eq(null))).thenReturn(json)
        val shuffledList = sharedPreferencesDataSource.shuffleWordsAndSave()
        val argumentCaptor = argumentCaptor<String>()
        verify(editor).putString(eq("get_word_key"), argumentCaptor.capture())
        val savedWordList = gson.fromJson(
            argumentCaptor.firstValue,
            object : TypeToken<List<Word>>() {}.type
        ) as List<Word>
        assertEquals(4, savedWordList.size)
        assertTrue(shuffledList.containsAll(wordList))
        if (shuffledList == wordList) {
            fail("The shuffled list is identical to the original list. This might happen occasionally, rerun the test.")
        } else {
            assertEquals(
                shuffledList,
                savedWordList
            )
        }
    }
}