package com.example.mytodoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mytodoapp.R
import com.example.mytodoapp.databinding.ActivityMainBinding
import android.view.ViewGroup
import com.example.mytodoapp.databinding.FilterSortSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val modalBottomSheet = ModalBottomSheet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(binding.navHostFragment.id)
        setupActionBarWithNavController(navController, AppBarConfiguration(setOf(R.id.todoListFragment, R.id.profileFragment)))
        binding.bottomNavbar.setupWithNavController(navController)
    }
}

class ModalBottomSheet : BottomSheetDialogFragment() {
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