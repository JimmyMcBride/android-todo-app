package com.example.mytodoapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mytodoapp.databinding.FragmentLoginBinding
import com.example.mytodoapp.extensions.logMe
import com.example.mytodoapp.repo.TodoRepo
import com.example.mytodoapp.repo.local.datastore.DatastorePreferences
import com.example.mytodoapp.viewmodel.AuthViewModel
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by activityViewModels<>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(
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
        btnCreateAccount.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.goToRegisterFragment())

        }
        btnLogin.setOnClickListener {
            lifecycleScope.launch {
                val response = AuthViewModel.login(tiUsername.text.toString(), tiPassword.text.toString())
                context?.let { DatastorePreferences(it) }?.setJWT(response.token)
                startActivity(Intent(context, AuthenticationRouter::class.java))
            }
        }

    }

//    private fun observationTower() = with(AuthViewModel) {
//        todos.observe(viewLifecycleOwner) { todos ->
//            todos.toString().logMe()
//        }
//    }


}