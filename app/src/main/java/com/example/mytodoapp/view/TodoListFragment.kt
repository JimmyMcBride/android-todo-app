package com.example.mytodoapp.view

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mytodoapp.R
import com.example.mytodoapp.adapter.TodoAdapter
import com.example.mytodoapp.databinding.FragmentTodoListBinding
import com.example.mytodoapp.extensions.logMe
import com.example.mytodoapp.models.Todo
import com.example.mytodoapp.repo.local.room.LocalTodoRepo
import com.example.mytodoapp.viewmodel.Filter
import com.example.mytodoapp.viewmodel.LocalTodoViewModel
import com.example.mytodoapp.viewmodel.Sort
import com.example.mytodoapp.viewmodel.TodoViewModel
import com.google.android.material.tabs.TabLayout

class TodoListFragment : Fragment() {
    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!

    private val token by lazy { activity?.intent?.getStringExtra("token")!! }
    private val localTodoViewModel: LocalTodoViewModel by activityViewModels() {
        LocalTodoViewModel.Factory(LocalTodoRepo(activity?.application!!))
    }

    private var isAsc = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sync_todos, menu);

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.sync_todos -> {
                mapTodosFromAPI()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }

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
//        btnFilterSort.setOnClickListener {
//            filterSort.show(childFragmentManager, FilterSortModal.TAG)
//        }
        fabAddTodo.setOnClickListener {
            findNavController().navigate(TodoListFragmentDirections.goToNewTodoFragment())
        }
//        btnSync.setOnClickListener {
//            mapTodosFromAPI()
//        }
        localTodoViewModel.filterTodos(Filter.NO_FILTER)
        localTodoViewModel.sortTodos(Sort.ID_ASC)
        btnSortCreated.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_up) }

        tabFilter.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                tab?.position.toString().logMe()
                when (tab?.position) {
                    0 -> localTodoViewModel.filterTodos(Filter.NO_FILTER)
                    1 -> localTodoViewModel.filterTodos(Filter.TODO_COMPLETED)
                    2 -> localTodoViewModel.filterTodos(Filter.TODO_UNCOMPLETED)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        btnSortCreated.setOnClickListener {
            btnSortTitle.icon = null
            btnSortUpdated.icon = null
            if (btnSortCreated.icon == null) {
                isAsc = false
            }
            if (isAsc) {
                btnSortCreated.icon =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.ic_down) }
                localTodoViewModel.sortTodos(Sort.ID_DESC)
                isAsc = false
            } else {
                btnSortCreated.icon =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.ic_up) }
                localTodoViewModel.sortTodos(Sort.ID_ASC)
                isAsc = true
            }
        }
        btnSortTitle.setOnClickListener {
            btnSortCreated.icon = null
            btnSortUpdated.icon = null
            if (btnSortTitle.icon == null) {
                isAsc = false
            }
            if (isAsc) {
                btnSortTitle.icon =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.ic_down) }
                localTodoViewModel.sortTodos(Sort.TITLE_DESC)
                isAsc = false
            } else {
                btnSortTitle.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_up) }
                localTodoViewModel.sortTodos(Sort.TITLE_ASC)
                isAsc = true
            }
        }
        btnSortUpdated.setOnClickListener {
            btnSortCreated.icon = null
            btnSortTitle.icon = null
            if (btnSortUpdated.icon == null) {
                isAsc = false
            }
            if (isAsc) {
                btnSortUpdated.icon =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.ic_down) }
                localTodoViewModel.sortTodos(Sort.UPDATED_DESC)
                isAsc = false
            } else {
                btnSortUpdated.icon =
                    context?.let { ContextCompat.getDrawable(it, R.drawable.ic_up) }
                localTodoViewModel.sortTodos(Sort.UPDATED_ASC)
                isAsc = true
            }
        }

    }

    private fun observationTower() = with(localTodoViewModel) {
        viewableTodos.observe(viewLifecycleOwner) { todos ->
            if (todos.isNullOrEmpty()) {
                binding.cvNoTodos.isVisible = true
                binding.svTodos.isVisible = false
            } else {
                binding.cvNoTodos.isVisible = false
                binding.svTodos.isVisible = true
                binding.rvTodos.adapter = TodoAdapter(todos, ::todoSelected)
            }
        }
    }

    private fun mapTodosFromAPI() = with(TodoViewModel(token)) {
        todos.observe(viewLifecycleOwner) { todos ->
            todos.map {
                localTodoViewModel.addTodo(it)
            }
        }
        binding.btnSortTitle.icon = null
        binding.btnSortUpdated.icon = null
        binding.btnSortCreated.icon =
            context?.let { ContextCompat.getDrawable(it, R.drawable.ic_up) }
        isAsc = true
        binding.tabFilter.getTabAt(0)?.select()
    }

    private fun todoSelected(todo: Todo) = with(findNavController()) {
        navigate(TodoListFragmentDirections.goToTodoDetailsFragment(todo))
    }
}