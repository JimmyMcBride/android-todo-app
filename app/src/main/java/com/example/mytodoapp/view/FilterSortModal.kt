package com.example.mytodoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mytodoapp.databinding.FilterSortSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterSortModal : BottomSheetDialogFragment() {
    private var _binding: FilterSortSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FilterSortSheetBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}