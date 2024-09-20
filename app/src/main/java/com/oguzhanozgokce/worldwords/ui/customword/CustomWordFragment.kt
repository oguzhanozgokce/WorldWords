package com.oguzhanozgokce.worldwords.ui.customword

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oguzhanozgokce.worldwords.common.BaseFragment
import com.oguzhanozgokce.worldwords.databinding.FragmentCustomWordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomWordFragment :
    BaseFragment<FragmentCustomWordBinding>(FragmentCustomWordBinding::inflate) {

    private val customWordViewModel: CustomWordViewModel by viewModels()

    override fun FragmentCustomWordBinding.bind() {
        btnSave.setOnClickListener {
            val englishWord = etEnglishWord.text.toString()
            val turkishWord = etTurkishWord.text.toString()
            val difficultyLevel = 1
            val imageUrl =
                "https://firebasestorage.googleapis.com/v0/b/ecommercecompose-6fe24.appspot.com/o/ic_custom.png?alt=media&token=84a8a7fb-a381-4f2c-81a0-b4deb5b1b9c5"

            if (englishWord.isNotEmpty() && turkishWord.isNotEmpty()) {
                customWordViewModel.addCustomWord(
                    turkishWord,
                    englishWord,
                    difficultyLevel,
                    imageUrl,
                    emptyList(),
                    emptyList()
                )
                Toast.makeText(requireContext(), "Word successfully added", Toast.LENGTH_SHORT)
                    .show()

                etEnglishWord.text?.clear()
                etTurkishWord.text?.clear()
            } else {
                Toast.makeText(requireContext(), "Free space available", Toast.LENGTH_SHORT).show()
            }
        }
        iwBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}