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
//        Word("kapı", "door", 1, R.drawable.door),
//        Word("pencere", "window", 1, R.drawable.window),
//        Word("ağaç", "tree", 1, R.drawable.tree),
//        Word("otobüs", "bus", 1, R.drawable.bus),
//        Word("deniz", "sea", 1, R.drawable.sea),
//        Word("park", "park", 1, R.drawable.park),
//        Word("dağ", "mountain", 1, R.drawable.mountain),
//        Word("toplantı", "meeting", 2, R.drawable.meeting),
        Word("öğrenci", "student", 2, R.drawable.ic_student),
//        Word("üniversite", "university", 2, R.drawable.university),
//        Word("yabancı", "foreigner", 2, R.drawable.foreigner),
//        Word("başarı", "success", 2, R.drawable.success),
//        Word("geliştirmek", "improve", 2, R.drawable.improve),
//        Word("teknoloji", "technology", 2, R.drawable.technology),
//        Word("müzik", "music", 2, R.drawable.music),
//        Word("konuşma", "conversation", 2, R.drawable.conversation),
//        Word("doğa", "nature", 2, R.drawable.nature),
//        Word("keşfetmek", "explore", 2, R.drawable.explore),
//        Word("araştırma", "research", 2, R.drawable.research),
//        Word("yaratıcı", "creative", 2, R.drawable.creative),
//        Word("kültür", "culture", 2, R.drawable.culture),
//        Word("tecrübe", "experience", 2, R.drawable.experience),
//        Word("felsefe", "philosophy", 2, R.drawable.philosophy),
//        Word("tatil", "vacation", 2, R.drawable.vacation),
//        Word("politik", "political", 2, R.drawable.political),
//        Word("ekonomi", "economy", 2, R.drawable.economy),
//        Word("turist", "tourist", 2, R.drawable.tourist),
//        Word("karmaşıklık", "complexity", 3, R.drawable.complexity),
//        Word("strateji", "strategy", 3, R.drawable.strategy),
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