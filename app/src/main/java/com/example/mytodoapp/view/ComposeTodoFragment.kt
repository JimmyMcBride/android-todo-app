package com.example.mytodoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodoapp.databinding.FragmentComposeTodoBinding
import com.example.mytodoapp.databinding.FragmentLoginBinding
import com.example.mytodoapp.models.AddTodoBody
import com.example.mytodoapp.models.UpdateTodoBody
import com.example.mytodoapp.viewmodel.TodoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ComposeTodoFragment : Fragment() {
    private var _binding: FragmentComposeTodoBinding? = null
    private val binding get() = _binding!!

    //    private val viewModel by activityViewModels<>()
    private val token by lazy { activity?.intent?.getStringExtra("token")!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentComposeTodoBinding.inflate(
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
        btnAddTodo.setOnClickListener {
            lifecycleScope.launch {
                TodoViewModel(token).addTodo(
                    AddTodoBody(
                        tiTodoTitle.text.toString(),
                        tiTodoDescription.text.toString(),
                        smCompleted.isChecked
                    )
                )
                Snackbar.make(binding.root, "Todo was successfully added.", Snackbar.LENGTH_SHORT)
                    .setAction("Close") {
                        // Responds to click on the action
                        it.visibility = View.GONE
                    }
                    .show()
                findNavController().navigate(EditTodoFragmentDirections.goToTodoListFragment())
            }
        }
    }
}