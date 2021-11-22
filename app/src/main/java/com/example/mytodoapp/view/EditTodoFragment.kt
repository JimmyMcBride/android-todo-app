package com.example.mytodoapp.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodoapp.databinding.FragmentEditTodoBinding
import com.example.mytodoapp.extensions.logMe
import com.example.mytodoapp.models.UpdateTodoBody
import com.example.mytodoapp.viewmodel.TodoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class EditTodoFragment : Fragment() {
    private var _binding: FragmentEditTodoBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<EditTodoFragmentArgs>()
    private val token by lazy { activity?.intent?.getStringExtra("token")!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentEditTodoBinding.inflate(
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
        tiTodoTitle.setText(args.todo.title)
        tiTodoDescription.setText(args.todo.description)
        smCompleted.isChecked = args.todo.completed
        btnEditTodo.setOnClickListener {
            lifecycleScope.launch {
                val todo = args.todo.id?.let {
                    TodoViewModel(token).editTodo(
                        it,
                        UpdateTodoBody(
                            tiTodoTitle.text.toString(),
                            tiTodoDescription.text.toString(),
                            smCompleted.isChecked
                        )
                    )
                }
                Snackbar.make(binding.root, "Todo was successfully edited.", Snackbar.LENGTH_SHORT)
                    .setAction("Close") {
                        // Responds to click on the action
                        it.visibility = View.GONE
                    }
                    .show()
                findNavController().navigate(EditTodoFragmentDirections.goToTodoListFragment())
            }
        }
        btnDeleteTodo.setOnClickListener {
            lifecycleScope.launch {
                args.todo.id?.let {
                    TodoViewModel(token).deleteTodo(
                        it
                    )
                }
                Snackbar.make(binding.root, "Todo was successfully deleted.", Snackbar.LENGTH_SHORT)
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