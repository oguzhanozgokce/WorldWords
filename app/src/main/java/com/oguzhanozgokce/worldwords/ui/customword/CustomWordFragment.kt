package com.oguzhanozgokce.worldwords.ui.customword

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.FragmentCustomWordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomWordFragment : Fragment() {
    private var _binding: FragmentCustomWordBinding? = null
    private val binding get() = _binding!!
    private val customWordViewModel: CustomWordViewModel by viewModels()
    private var selectedImageUri: Uri? = null

//    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        uri?.let {
//            selectedImageUri = it
//            binding.ivSelectedImage.setImageURI(it)
//        }
//    }

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

            if (turkishWord.isNotBlank() && englishWord.isNotBlank()) {
                val imageResId = R.drawable.ic_words
                customWordViewModel.addCustomWord(turkishWord, englishWord, imageResId)
                Toast.makeText(requireContext(), "Word added successfully!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

//    private fun openGalleryForImage() {
//        selectImageLauncher.launch("image/*") // Only allow image types
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}