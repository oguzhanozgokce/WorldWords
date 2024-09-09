package com.oguzhanozgokce.worldwords

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanozgokce.worldwords.databinding.ActivityMainBinding
import com.oguzhanozgokce.worldwords.ui.WordAdapter
import com.oguzhanozgokce.worldwords.ui.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val wordViewModel: WordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val wordAdapter = WordAdapter(emptyList())
        binding.rcWord.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = wordAdapter
        }

        lifecycleScope.launch {
            wordViewModel.wordList.collect { words ->
                wordAdapter.updateWords(words)
            }
        }
    }
}