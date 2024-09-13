package com.oguzhanozgokce.worldwords.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(private var inflate: (LayoutInflater, ViewGroup?, Boolean) -> T) : Fragment() {
    private var binding : T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.bind()
    }

    abstract fun T.bind()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
