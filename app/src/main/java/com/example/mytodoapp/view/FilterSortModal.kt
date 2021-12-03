package com.example.mytodoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import com.example.mytodoapp.R
import com.example.mytodoapp.databinding.FilterSortSheetBinding
import com.example.mytodoapp.extensions.logMe
import com.example.mytodoapp.repo.local.room.LocalTodoRepo
import com.example.mytodoapp.viewmodel.Filter
import com.example.mytodoapp.viewmodel.LocalTodoViewModel
import com.example.mytodoapp.viewmodel.Sort
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayout

class FilterSortModal : BottomSheetDialogFragment() {
    private var _binding: FilterSortSheetBinding? = null
    private val binding get() = _binding!!

    private val localTodoViewModel: LocalTodoViewModel by activityViewModels() {
        LocalTodoViewModel.Factory(LocalTodoRepo(activity?.application!!))
    }

    private var isAsc = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FilterSortSheetBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
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
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })

        btnSortCreated.setOnClickListener {
            btnSortTitle.icon = null
            btnSortUpdated.icon = null
            if (btnSortCreated.icon == null) {
                isAsc = false
            }
            if (isAsc) {
                btnSortCreated.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_down) }
                localTodoViewModel.sortTodos(Sort.ID_DESC)
                isAsc = false
            } else {
                btnSortCreated.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_up) }
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
                btnSortTitle.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_down) }
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
                btnSortUpdated.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_down) }
                localTodoViewModel.sortTodos(Sort.UPDATED_DESC)
                isAsc = false
            } else {
                btnSortUpdated.icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_up) }
                localTodoViewModel.sortTodos(Sort.UPDATED_ASC)
                isAsc = true
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}