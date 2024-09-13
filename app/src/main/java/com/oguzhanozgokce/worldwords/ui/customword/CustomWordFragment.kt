package com.oguzhanozgokce.worldwords.ui.customword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oguzhanozgokce.worldwords.databinding.FragmentCustomWordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomWordFragment : Fragment() {
    private var _binding: FragmentCustomWordBinding? = null
    private val binding get() = _binding!!
    private val customWordViewModel: CustomWordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomWordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val englishWord = binding.etEnglishWord.text.toString()
            val turkishWord = binding.etTurkishWord.text.toString()
            val difficultyLevel = 1
            val imageUrl = "https://firebasestorage.googleapis.com/v0/b/ecommercecompose-6fe24.appspot.com/o/ic_custom.png?alt=media&token=84a8a7fb-a381-4f2c-81a0-b4deb5b1b9c5"

            if (englishWord.isNotEmpty() && turkishWord.isNotEmpty()) {
                customWordViewModel.addCustomWord(turkishWord, englishWord, difficultyLevel, imageUrl)
                Toast.makeText(requireContext(), "Word successfully added", Toast.LENGTH_SHORT).show()

                binding.etEnglishWord.text?.clear()
                binding.etTurkishWord.text?.clear()
            } else {
                Toast.makeText(requireContext(), "Free space available", Toast.LENGTH_SHORT).show()
            }
        }

        binding.iwBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}