package com.oguzhanozgokce.worldwords.data

import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.model.Word
import javax.inject.Inject

class WordRepository @Inject constructor(private val sharedPreferencesDataSource: SharedPreferencesDataSource) {
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
        Word("kültür", "culture", 2, R.drawable.ic_culture),
        Word("tecrübe", "experience", 2, R.drawable.ic_experience),
        Word("felsefe", "philosophy", 2, R.drawable.ic_philosophy),
        Word("tatil", "vacation", 2, R.drawable.ic_vacation),
        Word("ekonomi", "economy", 2, R.drawable.ic_economic),
        Word("karmaşıklık", "complexity", 3, R.drawable.ic_complexity),
        Word("strateji", "strategy", 3, R.drawable.ic_strategy),
        Word("analiz", "analysis", 3, R.drawable.ic_analysis),
        Word("planlama", "planning", 3, R.drawable.ic_planning)
    )

    private val usageExampleMap = mapOf(
        "apple" to listOf(
            "I eat an apple every day.",
            "She bought a red apple from the store.",
            "The apple fell from the tree during the storm."
        ),
        "book" to listOf(
            "I read a book last night.",
            "This book is about world history.",
            "He is writing a book on environmental issues."
        ),
        "car" to listOf(
            "I have a blue car.",
            "The car stopped at the red light.",
            "The new electric car can drive over 300 miles on a single charge."
        ),
        "table" to listOf(
            "The table is made of wood.",
            "She placed the dishes on the table.",
            "They discussed the plan around the conference table."
        ),
        "pen" to listOf(
            "I need a pen to sign the paper.",
            "The pen ran out of ink.",
            "He wrote a letter using his favorite fountain pen."
        ),
        "computer" to listOf(
            "She bought a new computer.",
            "The computer is very fast.",
            "He is working on a complex software project on his computer."
        ),
        "cat" to listOf(
            "The cat is sleeping on the couch.",
            "My cat loves to play with the yarn.",
            "The black cat quickly climbed the tall tree."
        ),
        "dog" to listOf(
            "I have a small dog.",
            "The dog barked at the stranger.",
            "The dog quickly learned the new trick."
        ),
        "water" to listOf(
            "Please drink some water.",
            "The water in the lake is very clear.",
            "He poured a glass of water from the pitcher."
        ),
        "bread" to listOf(
            "I like to eat fresh bread.",
            "The bakery sells delicious bread.",
            "He spread butter on the warm slice of bread."
        ),
        "flower" to listOf(
            "The flower is red.",
            "She picked a beautiful flower from the garden.",
            "The flowers in the field bloom in the spring."
        ),
        "glasses" to listOf(
            "She wears glasses.",
            "I need my glasses to read.",
            "His new glasses have a blue light filter for computer screens."
        ),
        "phone" to listOf(
            "I use my phone to call my friend.",
            "The phone rang during the meeting.",
            "She sent a message through her phone’s messaging app."
        ),
        "building" to listOf(
            "This is a tall building.",
            "The office is located in the tallest building downtown.",
            "The building was constructed using eco-friendly materials."
        ),
        "plane" to listOf(
            "The plane is landing.",
            "They boarded the plane early.",
            "The plane flew over the mountains and through the clouds."
        ),
        "bicycle" to listOf(
            "I ride my bicycle every day.",
            "She bought a new bicycle for her commute.",
            "His bicycle is equipped with gears for mountain biking."
        ),
        "hand" to listOf(
            "My hand is cold.",
            "She raised her hand to ask a question.",
            "He offered his hand to help her up after she fell."
        ),
        "sun" to listOf(
            "The sun is shining.",
            "The sun sets in the west.",
            "The sun’s rays provide energy for the plants to grow."
        ),
        "bag" to listOf(
            "I carry a bag to school.",
            "She packed her lunch in a small bag.",
            "The bag was heavy with books and supplies."
        ),
        "bed" to listOf(
            "I sleep in my bed.",
            "The bed is very comfortable.",
            "She made the bed with clean sheets and a warm blanket."
        ),
        "shoe" to listOf(
            "I wear my shoes to school.",
            "Her new shoes are very comfortable.",
            "He tied the laces on his running shoes tightly before the race."
        ),
        "chair" to listOf(
            "I sit on a chair.",
            "The chair in the living room is very soft.",
            "He bought a new office chair for better back support."
        ),
        "food" to listOf(
            "I love eating Italian food.",
            "The food was delicious and fresh.",
            "They donated food to the local shelter every week."
        ),
        "door" to listOf(
            "Close the door, please.",
            "The door is painted red.",
            "The front door creaked as it opened slowly."
        ),
        "window" to listOf(
            "Open the window for fresh air.",
            "She looked out the window and saw the rain.",
            "The window shattered when the ball hit it."
        ),
        "tree" to listOf(
            "The tree is very tall.",
            "The tree in my backyard grows apples.",
            "In the fall, the leaves of the tree turn bright orange."
        ),
        "bus" to listOf(
            "I take the bus to school.",
            "The bus was late this morning.",
            "He missed the bus and had to walk home."
        ),
        "sea" to listOf(
            "The sea is calm today.",
            "We sailed across the sea to a nearby island.",
            "The sea is home to many different marine species."
        ),
        "park" to listOf(
            "We went to the park to play.",
            "The park is full of people on weekends.",
            "They took a long walk through the park at sunset."
        ),
        "mountain" to listOf(
            "The mountain is covered in snow.",
            "We hiked up the mountain trail.",
            "The view from the top of the mountain was breathtaking."
        ),
        "meeting" to listOf(
            "The meeting starts at 10 AM.",
            "We had a long meeting about the new project.",
            "The meeting concluded with a detailed action plan."
        ),
        "student" to listOf(
            "I am a student at the university.",
            "The student asked a question during the lecture.",
            "She is the top student in her class, always excelling in exams."
        ),
        "university" to listOf(
            "I attend university in the city.",
            "The university offers many different courses.",
            "He graduated from one of the top universities in the country."
        ),
        "foreigner" to listOf(
            "He is a foreigner in this country.",
            "The foreigner asked for directions to the nearest hotel.",
            "As a foreigner, it took him some time to adjust to the new culture."
        ),
        "success" to listOf(
            "Success requires hard work.",
            "She achieved great success in her career.",
            "His success is a result of years of dedication and perseverance."
        ),
        "improve" to listOf(
            "I want to improve my English.",
            "He worked hard to improve his performance at work.",
            "The new system was designed to improve efficiency and reduce costs."
        ),
        "technology" to listOf(
            "Technology is advancing rapidly.",
            "The new technology has revolutionized the industry.",
            "He is researching how technology can improve healthcare systems."
        ),
        "music" to listOf(
            "I love listening to music.",
            "She plays music on her guitar every evening.",
            "The music industry has changed drastically with the rise of streaming services."
        ),
        "conversation" to listOf(
            "We had a short conversation.",
            "Their conversation lasted for hours.",
            "The conversation covered a wide range of topics, from politics to philosophy."
        ),
        "nature" to listOf(
            "Nature is beautiful.",
            "We spent the day exploring nature in the national park.",
            "The beauty of nature can inspire creativity and calm the mind."
        ),
        "explore" to listOf(
            "I love to explore new places.",
            "They set out to explore the unknown regions of the forest.",
            "He spent years exploring the depths of the ocean in search of new species."
        ),
        "culture" to listOf(
            "Every country has its own culture.",
            "We learned a lot about Japanese culture during our visit.",
            "The festival celebrates the rich and diverse culture of the local community."
        ),
        "experience" to listOf(
            "I had a great experience at the event.",
            "The trip was a memorable experience.",
            "His years of experience in the field made him the ideal candidate for the job."
        ),
        "philosophy" to listOf(
            "He is studying philosophy at university.",
            "Philosophy explores fundamental questions about life and existence.",
            "Her book offers a new perspective on ancient Greek philosophy."
        ),
        "vacation" to listOf(
            "We went on a vacation last summer.",
            "The family planned a two-week vacation in Italy.",
            "They spent their vacation exploring the hidden beaches of the tropical island."
        ),
        "economy" to listOf(
            "The economy is growing.",
            "Experts are analyzing the effects of inflation on the global economy.",
            "The country’s economy relies heavily on exports and international trade."
        ),
        "complexity" to listOf(
            "The problem’s complexity makes it hard to solve.",
            "The complexity of the issue requires careful consideration.",
            "His research focuses on the complexity of human behavior and decision-making."
        ),
        "strategy" to listOf(
            "We need a new strategy.",
            "The company's strategy focuses on long-term growth.",
            "Developing an effective strategy requires a deep understanding of the market."
        ),
        "analysis" to listOf(
            "We did an analysis of the data.",
            "The report includes an in-depth analysis of the financial statements.",
            "His analysis of the situation provided valuable insights for the project."
        ),
        "planning" to listOf(
            "Planning is important for success.",
            "She is responsible for planning the event.",
            "Effective planning ensures that resources are allocated efficiently."
        )
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

    fun getUsageExamples(word: Word): List<String>? {
        return usageExampleMap[word.english]
    }

    fun addWordToLearnedList(word: Word) {
        sharedPreferencesDataSource.addLearnedWord(word)
    }

    fun getLearnedWords(): List<Word> {
        return sharedPreferencesDataSource.getLearnedWords()
    }

    fun removeWordFromLearnedList(word: Word) {
        sharedPreferencesDataSource.removeLearnedWord(word)
    }

    fun isWordInLearnedList(word: Word): Boolean {
        return sharedPreferencesDataSource.isWordInLearnedList(word)
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

}