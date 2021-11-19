package com.example.mytodoapp.view

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodoapp.databinding.FragmentTodoDetailsBinding
import com.example.mytodoapp.extensions.convertToDateTimeStamp
import com.example.mytodoapp.extensions.logMe
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDateTime
import javax.xml.datatype.DatatypeFactory

class TodoDetailsFragment : Fragment() {
    private var _binding: FragmentTodoDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<TodoDetailsFragmentArgs>()
//    private val viewModel by activityViewModels<>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentTodoDetailsBinding.inflate(
        inflater, container, false
    ).also { _binding = it }.root

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() = with(binding) {
        tvTodoTitle.text = args.todo.title
        if (args.todo.description.isNullOrBlank()) {
            tvTodoDescription.setTypeface(null, Typeface.ITALIC)
            tvTodoDescription.text = "This todo does not have a description..."
        } else {
            tvTodoDescription.text = args.todo.description
        }

        tvTodoCompleted.text = args.todo.completed.toString()
        tvTodoDate.text = args.todo.date.convertToDateTimeStamp()
        tvTodoUpdatedAt.text = args.todo.updatedAt.convertToDateTimeStamp()

        btnEditTodo.setOnClickListener {
            findNavController().navigate(TodoDetailsFragmentDirections.goToEditTodoFragment(args.todo))
        }
    }
}