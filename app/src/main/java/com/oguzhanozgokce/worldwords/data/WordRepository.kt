package com.oguzhanozgokce.worldwords.data

import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.model.Word

class WordRepository {
    private val wordList = mutableListOf(
        Word("elma", "apple", 1, R.drawable.ic_apple),
        Word("kitap", "book", 1, R.drawable.ic_book),
        Word("araba", "car", 1, R.drawable.ic_car),
        Word("masa", "table", 1, R.drawable.ic_table),
        Word("kalem", "pen", 1, R.drawable.ic_pen),
        Word("bilgisayar", "computer", 1, R.drawable.ic_computer),
        Word("kedi", "cat", 1, R.drawable.ic_cat),
        Word("köpek", "dog", 1, R.drawable.ic_dog),
        Word("su", "water", 1, R.drawable.ic_water),
        Word("ekmek", "bread", 1, R.drawable.ic_bread),
        Word("çiçek", "flower", 1, R.drawable.ic_flower),
        Word("gözlük", "glasses", 1, R.drawable.ic_glasses),
        Word("telefon", "phone", 1, R.drawable.ic_phone),
        Word("bina", "building", 1, R.drawable.ic_building),
        Word("uçak", "plane", 1, R.drawable.ic_plane),
        Word("bisiklet", "bicycle", 1, R.drawable.ic_bicycle),
        Word("el", "hand", 1, R.drawable.ic_hand),
        Word("güneş", "sun", 1, R.drawable.ic_sun),
        Word("çanta", "bag", 1, R.drawable.ic_bag),
        Word("yatak", "bed", 1, R.drawable.ic_bed),
        Word("ayakkabı", "shoe", 1, R.drawable.ic_shoe),
        Word("sandalye", "chair", 1, R.drawable.ic_chair),
        Word("yemek", "food", 1, R.drawable.ic_food),
        Word("kapı", "door", 1, R.drawable.ic_door),
        Word("pencere", "window", 1, R.drawable.ic_window),
        Word("ağaç", "tree", 1, R.drawable.ic_tree),
        Word("otobüs", "bus", 1, R.drawable.ic_bus),
        Word("deniz", "sea", 1, R.drawable.ic_sea),
        Word("park", "park", 1, R.drawable.ic_park),
        Word("dağ", "mountain", 1, R.drawable.ic_mountain),
        Word("toplantı", "meeting", 2, R.drawable.ic_meet),
        Word("öğrenci", "student", 2, R.drawable.ic_student),
        Word("üniversite", "university", 2, R.drawable.ic_university),
        Word("yabancı", "foreigner", 2, R.drawable.ic_foreigner),
        Word("başarı", "success", 2, R.drawable.ic_success),
        Word("geliştirmek", "improve", 2, R.drawable.ic_improve),
        Word("teknoloji", "technology", 2, R.drawable.ic_technology),
        Word("müzik", "music", 2, R.drawable.ic_music),
        Word("sohbet", "conversation", 2, R.drawable.ic_conversation),
        Word("doğa", "nature", 2, R.drawable.ic_nature),
        Word("keşfetmek", "explore", 2, R.drawable.ic_explore),
//        Word("yaratıcı", "creative", 2, R.drawable.creative),
        Word("kültür", "culture", 2, R.drawable.ic_culture),
        Word("tecrübe", "experience", 2, R.drawable.ic_experience),
        Word("felsefe", "philosophy", 2, R.drawable.ic_philosophy),
        Word("tatil", "vacation", 2, R.drawable.ic_vacation),
        Word("ekonomi", "economy", 2, R.drawable.ic_economic),
        Word("karmaşıklık", "complexity", 3, R.drawable.ic_complexity),
        Word("strateji", "strategy", 3, R.drawable.ic_strategy),
//        Word("motivasyon", "motivation", 3, R.drawable.motivation),
//        Word("yenilik", "innovation", 3, R.drawable.innovation),
//        Word("etkileşim", "interaction", 3, R.drawable.interaction),
//        Word("tartışma", "discussion", 3, R.drawable.discussion),
//        Word("sorumluluk", "responsibility", 3, R.drawable.responsibility),
//        Word("kararlılık", "determination", 3, R.drawable.determination),
        Word("analiz", "analysis", 3, R.drawable.ic_analysis),
        Word("planlama", "planning", 3, R.drawable.ic_planning)
    )

    fun getWords(): List<Word> {
        return wordList
    }

    fun addWord(word: Word) {
        wordList.add(word)
    }

    fun removeWord(word: Word) {
        wordList.remove(word)
    }

    fun filterWords(difficulty: Int): List<Word> {
        return wordList.filter { it.difficulty == difficulty }
    }

    fun shuffleWords(): List<Word> {
        return wordList.shuffled()
    }
}