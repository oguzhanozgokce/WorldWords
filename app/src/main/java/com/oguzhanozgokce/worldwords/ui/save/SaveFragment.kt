package com.oguzhanozgokce.worldwords.ui.save

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanozgokce.worldwords.R
import com.oguzhanozgokce.worldwords.databinding.FragmentSaveBinding
import com.oguzhanozgokce.worldwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveFragment : Fragment() {
    private var _binding: FragmentSaveBinding? = null
    private val binding get() = _binding!!
    private val saveViewModel: SaveViewModel by viewModels()
    private lateinit var saveAdapter: SaveAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaveBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeSavedWords()
        saveViewModel.getSavedWords()
    }

    private fun setupRecyclerView() {
        saveAdapter = SaveAdapter(emptyList()) { word ->
            navigateToWordDetail(word)
        }

        binding.rwSave.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = saveAdapter
        }
    }

    private fun observeSavedWords() {
        viewLifecycleOwner.lifecycleScope.launch {
            saveViewModel.saveWordList.collect { savedWords ->
                saveAdapter.updateWords(savedWords)
            }
        }
    }

    private fun navigateToWordDetail(word: Word) {
        val action = SaveFragmentDirections.actionSaveFragmentToWordDetailFragment(word)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}