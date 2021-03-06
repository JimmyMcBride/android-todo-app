package com.example.mytodoapp.adapter

import android.graphics.Typeface
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodoapp.R
import com.example.mytodoapp.databinding.TodoCardBinding
import com.example.mytodoapp.extensions.layoutInflater
import com.example.mytodoapp.models.Todo

class TodoAdapter(
    private val todos: List<Todo>,
    private val todoSelected: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = TodoViewHolder.getInstance(parent).apply {
        itemView.setOnClickListener { todoSelected(todos[adapterPosition]) }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.loadRecipes(todos[position])
    }

    override fun getItemCount() = todos.size

    class TodoViewHolder(
        private val binding: TodoCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadRecipes(todo: Todo) = with(binding) {
            tvTodoTitle.text = todo.title
            if (todo.description.isNullOrBlank()) {
                tvTodoDescription.setTypeface(null, Typeface.ITALIC)
                tvTodoDescription.text = "This todo does not have a description..."
            } else {
                tvTodoDescription.text = todo.description
            }
            if (todo.completed) {
                tvTodoTitle.setTextColor(ContextCompat.getColor(binding.root.context, R.color.red_700))
                tvTodoDescription.setTextColor(ContextCompat.getColor(binding.root.context, R.color.red_700))
            }
        }

        companion object {
            fun getInstance(parent: ViewGroup) = TodoCardBinding.inflate(
                parent.layoutInflater, parent, false
            ).run { TodoViewHolder(this) }
        }
    }
}