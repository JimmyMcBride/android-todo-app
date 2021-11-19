package com.example.mytodoapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.mytodoapp.databinding.FragmentProfileBinding
import com.example.mytodoapp.repo.local.datastore.DatastorePreferences
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by activityViewModels<>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentProfileBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() = with(binding) {
        btnLogout.setOnClickListener {
            lifecycleScope.launch {
                context?.let { DatastorePreferences(it) }?.setJWT("")
                startActivity(Intent(context, AuthenticationRouter::class.java))
            }
        }
    }
}