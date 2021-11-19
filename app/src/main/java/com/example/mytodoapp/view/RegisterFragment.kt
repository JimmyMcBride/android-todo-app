package com.example.mytodoapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mytodoapp.R
import com.example.mytodoapp.databinding.FragmentLoginBinding
import com.example.mytodoapp.databinding.FragmentRegisterBinding
import com.example.mytodoapp.repo.local.datastore.DatastorePreferences
import com.example.mytodoapp.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by activityViewModels<>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentRegisterBinding.inflate(
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
        btnBackToLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.goToFragment())
        }
        btnRegister.setOnClickListener {
            lifecycleScope.launch {
                val response = AuthViewModel.register(tiUsername.text.toString(), tiEmail.text.toString(), tiPassword.text.toString())
                context?.let { DatastorePreferences(it) }?.setJWT(response.token)
                startActivity(Intent(context, AuthenticationRouter::class.java))
            }
        }
    }
}