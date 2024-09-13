package com.oguzhanozgokce.worldwords.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WordRepository @Inject constructor(private val sharedPreferencesDataSource: SharedPreferencesDataSource, @ApplicationContext private val context: Context) {

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

    private val usageExampleTurkishMap = mapOf(
        "apple" to listOf(
            "Her gün bir elma yiyorum.",
            "Mağazadan kırmızı bir elma aldı.",
            "Fırtına sırasında elma ağaçtan düştü."
        ),
        "book" to listOf(
            "Dün gece bir kitap okudum.",
            "Bu kitap dünya tarihi hakkında.",
            "Çevre sorunları hakkında bir kitap yazıyor."
        ),
        "car" to listOf(
            "Mavi bir arabam var.",
            "Araba kırmızı ışıkta durdu.",
            "Yeni elektrikli araba tek şarjla 300 milin üzerinde yol alabiliyor."
        ),
        "table" to listOf(
            "Masa ahşaptan yapılmıştır.",
            "Tabakları masaya koydu.",
            "Konferans masasında planı tartıştılar."
        ),
        "pen" to listOf(
            "Kağıdı imzalamak için bir kaleme ihtiyacım var.",
            "Kalemin mürekkebi bitti.",
            "En sevdiği dolma kalemle bir mektup yazdı."
        ),
        "computer" to listOf(
            "Yeni bir bilgisayar aldı.",
            "Bilgisayar çok hızlı.",
            "Bilgisayarında karmaşık bir yazılım projesi üzerinde çalışıyor."
        ),
        "cat" to listOf(
            "Kedi kanepede uyuyor.",
            "Kedim iplikle oynamayı çok seviyor.",
            "Siyah kedi hızlıca yüksek ağaca tırmandı."
        ),
        "dog" to listOf(
            "Küçük bir köpeğim var.",
            "Köpek yabancıya havladı.",
            "Köpek yeni numarayı hızla öğrendi."
        ),
        "water" to listOf(
            "Lütfen biraz su iç.",
            "Göldeki su çok berrak.",
            "Sürahiden bir bardak su döktü."
        ),
        "bread" to listOf(
            "Taze ekmek yemeyi seviyorum.",
            "Fırın lezzetli ekmekler satıyor.",
            "Sıcak ekmek diliminin üzerine tereyağı sürdü."
        ),
        "flower" to listOf(
            "Çiçek kırmızı.",
            "Bahçeden güzel bir çiçek kopardı.",
            "Tarladaki çiçekler ilkbaharda açar."
        ),
        "glasses" to listOf(
            "Gözlük takıyor.",
            "Okumak için gözlüğe ihtiyacım var.",
            "Yeni gözlüklerinde mavi ışık filtresi var."
        ),
        "phone" to listOf(
            "Arkadaşımı aramak için telefonumu kullanıyorum.",
            "Telefon toplantı sırasında çaldı.",
            "Mesajlaşma uygulaması aracılığıyla bir mesaj gönderdi."
        ),
        "building" to listOf(
            "Bu yüksek bir bina.",
            "Ofis şehir merkezindeki en yüksek binada yer alıyor.",
            "Bina çevre dostu malzemelerle inşa edildi."
        ),
        "plane" to listOf(
            "Uçak iniş yapıyor.",
            "Uçağa erken bindiler.",
            "Uçak dağların üzerinden ve bulutların arasından geçti."
        ),
        "bicycle" to listOf(
            "Her gün bisiklet sürerim.",
            "İşe gitmek için yeni bir bisiklet aldı.",
            "Bisikletinde dağ bisikleti için vitesler var."
        ),
        "hand" to listOf(
            "Elim üşüyor.",
            "Soru sormak için elini kaldırdı.",
            "Düştükten sonra ona elini uzattı."
        ),
        "sun" to listOf(
            "Güneş parlıyor.",
            "Güneş batıda batar.",
            "Güneş ışınları bitkilerin büyümesi için enerji sağlar."
        ),
        "bag" to listOf(
            "Okula bir çanta taşıyorum.",
            "Öğle yemeğini küçük bir çantaya koydu.",
            "Çanta kitaplar ve malzemelerle ağırdı."
        ),
        "bed" to listOf(
            "Yatağımda uyurum.",
            "Yatak çok rahat.",
            "Temiz çarşaflar ve sıcak bir battaniye ile yatağı yaptı."
        ),
        "shoe" to listOf(
            "Okula giderken ayakkabılarımı giyerim.",
            "Yeni ayakkabıları çok rahat.",
            "Koşu ayakkabılarının bağcıklarını yarış öncesi sıkıca bağladı."
        ),
        "chair" to listOf(
            "Bir sandalyeye oturuyorum.",
            "Oturma odasındaki sandalye çok yumuşak.",
            "Daha iyi sırt desteği için yeni bir ofis sandalyesi aldı."
        ),
        "food" to listOf(
            "İtalyan yemeklerini yemeyi seviyorum.",
            "Yemek çok lezzetli ve tazeydi.",
            "Her hafta yerel barınağa yemek bağışladılar."
        ),
        "door" to listOf(
            "Kapıyı kapat, lütfen.",
            "Kapı kırmızıya boyandı.",
            "Ön kapı yavaşça açılırken gıcırdadı."
        ),
        "window" to listOf(
            "Temiz hava almak için pencereyi aç.",
            "Yağmuru görmek için pencereye baktı.",
            "Top vurduğunda pencere kırıldı."
        ),
        "tree" to listOf(
            "Ağaç çok uzun.",
            "Arka bahçemdeki ağaç elma veriyor.",
            "Sonbaharda, ağacın yaprakları parlak turuncuya döner."
        ),
        "bus" to listOf(
            "Okula otobüsle giderim.",
            "Otobüs bu sabah geç kaldı.",
            "Otobüsü kaçırdı ve eve yürümek zorunda kaldı."
        ),
        "sea" to listOf(
            "Deniz bugün sakin.",
            "Yakındaki bir adaya deniz üzerinden yelken açtık.",
            "Deniz birçok farklı deniz canlısına ev sahipliği yapar."
        ),
        "park" to listOf(
            "Oynamak için parka gittik.",
            "Park hafta sonları insanlarla dolu.",
            "Gün batımında parkta uzun bir yürüyüş yaptılar."
        ),
        "mountain" to listOf(
            "Dağ karla kaplı.",
            "Dağ yolunda yürüyüş yaptık.",
            "Dağın zirvesinden manzara nefes kesiciydi."
        ),
        "meeting" to listOf(
            "Toplantı sabah 10'da başlıyor.",
            "Yeni proje hakkında uzun bir toplantı yaptık.",
            "Toplantı, ayrıntılı bir eylem planı ile sonuçlandı."
        ),
        "student" to listOf(
            "Ben üniversitede öğrenciyim.",
            "Öğrenci ders sırasında bir soru sordu.",
            "Sınıfın en iyi öğrencisi, her zaman sınavlarda başarılı oluyor."
        ),
        "university" to listOf(
            "Şehirdeki üniversitede okuyorum.",
            "Üniversite birçok farklı kurs sunuyor.",
            "Ülkenin en iyi üniversitelerinden birinden mezun oldu."
        ),
        "foreigner" to listOf(
            "Bu ülkede bir yabancı.",
            "Yabancı, en yakın otele nasıl gideceğini sordu.",
            "Bir yabancı olarak, yeni kültüre alışması biraz zaman aldı."
        ),
        "success" to listOf(
            "Başarı, sıkı çalışma gerektirir.",
            "Kariyerinde büyük başarı elde etti.",
            "Başarısı, yılların adanmışlık ve sabrının bir sonucudur."
        ),
        "improve" to listOf(
            "İngilizcemi geliştirmek istiyorum.",
            "İşyerinde performansını artırmak için çok çalıştı.",
            "Yeni sistem, verimliliği artırmak ve maliyetleri düşürmek için tasarlandı."
        ),
        "technology" to listOf(
            "Teknoloji hızla ilerliyor.",
            "Yeni teknoloji sektörde devrim yarattı.",
            "Sağlık sistemlerini iyileştirmek için teknolojinin nasıl kullanılacağını araştırıyor."
        ),
        "music" to listOf(
            "Müzik dinlemeyi seviyorum.",
            "Her akşam gitarıyla müzik çalıyor.",
            "Müzik endüstrisi, yayın hizmetlerinin yükselişiyle birlikte büyük ölçüde değişti."
        ),
        "conversation" to listOf(
            "Kısa bir sohbet yaptık.",
            "Sohbetleri saatler sürdü.",
            "Sohbet, politikadan felsefeye kadar geniş bir yelpazeyi kapsıyordu."
        ),
        "nature" to listOf(
            "Doğa çok güzel.",
            "Milli parkta doğayı keşfederek bir gün geçirdik.",
            "Doğanın güzelliği yaratıcılığı teşvik edebilir ve zihni sakinleştirebilir."
        ),
        "explore" to listOf(
            "Yeni yerler keşfetmeyi seviyorum.",
            "Ormanın bilinmeyen bölgelerini keşfetmek için yola çıktılar.",
            "Okyanusun derinliklerini keşfederek yeni türler aradı."
        ),
        "culture" to listOf(
            "Her ülkenin kendi kültürü vardır.",
            "Japonya'yı ziyaretimiz sırasında Japon kültürü hakkında çok şey öğrendik.",
            "Festival, yerel topluluğun zengin ve çeşitli kültürünü kutluyor."
        ),
        "experience" to listOf(
            "Etkinlikte harika bir deneyim yaşadım.",
            "Gezi unutulmaz bir deneyimdi.",
            "Sahadaki yıllara dayanan deneyimi, onu bu iş için ideal aday yaptı."
        ),
        "philosophy" to listOf(
            "Üniversitede felsefe okuyor.",
            "Felsefe, hayat ve varoluşla ilgili temel soruları araştırır.",
            "Kitabı, antik Yunan felsefesine yeni bir bakış açısı sunuyor."
        ),
        "vacation" to listOf(
            "Geçen yaz tatil yaptık.",
            "Aile İtalya'da iki haftalık bir tatil planladı.",
            "Tatilini tropikal adanın gizli plajlarını keşfederek geçirdiler."
        ),
        "economy" to listOf(
            "Ekonomi büyüyor.",
            "Uzmanlar, enflasyonun küresel ekonomi üzerindeki etkilerini analiz ediyor.",
            "Ülkenin ekonomisi büyük ölçüde ihracat ve uluslararası ticarete dayanıyor."
        ),
        "complexity" to listOf(
            "Sorunun karmaşıklığı çözülmesini zorlaştırıyor.",
            "Konunun karmaşıklığı dikkatli düşünmeyi gerektiriyor.",
            "Araştırması, insan davranışı ve karar verme sürecinin karmaşıklığına odaklanıyor."
        ),
        "strategy" to listOf(
            "Yeni bir stratejiye ihtiyacımız var.",
            "Şirketin stratejisi uzun vadeli büyümeye odaklanıyor.",
            "Etkili bir strateji geliştirmek, piyasayı derinlemesine anlamayı gerektirir."
        ),
        "analysis" to listOf(
            "Verilerin analizini yaptık.",
            "Rapor, mali tabloların derinlemesine bir analizini içeriyor.",
            "Durumun analizi, projeye değerli bilgiler sağladı."
        ),
        "planning" to listOf(
            "Planlama, başarı için önemlidir.",
            "Etkinliği planlamaktan sorumlu.",
            "Etkili planlama, kaynakların verimli bir şekilde tahsis edilmesini sağlar."
        )
    )


    fun getWords(): List<Word> {
        return sharedPreferencesDataSource.getWordsFromSharedPreferences()
    }

    fun addWord(word: Word) {
        sharedPreferencesDataSource.addWordToSharedPreferences(word)
    }

    fun removeWord(word: Word) {
        sharedPreferencesDataSource.removeWordFromSharedPreferences(word)
    }

    fun addCustomWord(turkish: String, english: String, difficulty: Int, imageUrl: String) {
        val newWord = Word(turkish, english, difficulty, imageUrl)
        sharedPreferencesDataSource.addWordToSharedPreferences(newWord)
    }


    fun shuffleWords(): List<Word> {
        return sharedPreferencesDataSource.shuffleWordsAndSave()
    }

    fun getUsageExamples(word: Word): List<String>? {
        return usageExampleMap[word.english]
    }

    fun getUsageTurkishExamples(word: Word): List<String>? {
        return usageExampleTurkishMap[word.english]
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