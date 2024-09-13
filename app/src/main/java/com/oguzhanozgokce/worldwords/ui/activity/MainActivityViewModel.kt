package com.oguzhanozgokce.worldwords.ui.activity

import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.worldwords.data.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

     fun saveJsonDataIfFirstTime() {
        wordRepository.saveJsonDataToSharedPrefsIfFirstTime()
    }
}
