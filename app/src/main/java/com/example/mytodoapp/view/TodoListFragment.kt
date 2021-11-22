package com.example.mytodoapp.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytodoapp.adapter.TodoAdapter
import com.example.mytodoapp.databinding.FragmentTodoListBinding
import com.example.mytodoapp.extensions.logMe
import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.repo.local.room.LocalTodoRepo
import com.example.mytodoapp.viewmodel.LocalTodoViewModel
import com.example.mytodoapp.viewmodel.TodoViewModel
import kotlinx.datetime.toLocalDate

class TodoListFragment : Fragment() {
    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private val token by lazy { activity?.intent?.getStringExtra("token")!! }
    private val filterSort = FilterSortModal()
//    private val localTodoViewModel: LocalTodoViewModel by viewModels {
//        LocalTodoViewModel.Factory(LocalTodoRepo(activity?.application!!))
//    }
//    private val viewModel by activityViewModels<>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentTodoListBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observationTower()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() = with(binding) {
        btnFilterSort.setOnClickListener {
            filterSort.show(childFragmentManager, FilterSortModal.TAG)
        }
        fabAddTodo.setOnClickListener {
            findNavController().navigate(TodoListFragmentDirections.goToNewTodoFragment())
        }

    }

    private fun observationTower() = with(TodoViewModel(token)) {
        todos.observe(viewLifecycleOwner) { todos ->
            todos.toString().logMe()
            binding.rvTodos.adapter = TodoAdapter(todos, ::todoSelected)
        }
    }

    private fun todoSelected(todo: Todo) = with(findNavController()) {
        navigate(TodoListFragmentDirections.goToTodoDetailsFragment(todo))
    }
}