package com.oguzhanozgokce.worldwords.ui.word

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanozgokce.worldwords.databinding.FragmentWordBinding
import com.oguzhanozgokce.worldwords.ui.WordAdapter
import com.oguzhanozgokce.worldwords.ui.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordFragment : Fragment() {
    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!
    private val wordViewModel: WordViewModel by viewModels()
    private lateinit var wordAdapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordAdapter = WordAdapter(emptyList())

        binding.rwWord.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = wordAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            wordViewModel.wordList.collect { words ->
                wordAdapter.updateWords(words)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}