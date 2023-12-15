package com.example.taskslist.ui.tabs.todoTasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskslist.data.database.Task
import com.example.taskslist.databinding.TodoTaskItemBinding

class ToDoTasksAdapter(private var taskList : MutableList<Task>? = null):RecyclerView.Adapter<ToDoTasksAdapter.ToDoTasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoTasksViewHolder {

        val viewBinding = TodoTaskItemBinding.inflate(LayoutInflater.from(parent.context)
            ,parent,false )
        return ToDoTasksViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ToDoTasksViewHolder, position: Int) {
        val task = taskList!![position]
        holder.viewBinding.taskContent.text = task.taskContent

        holder.viewBinding.menu.setOnClickListener{
            onMenuClickListener.onItemClick(position,task)
        }
        holder.viewBinding.checkbox.isChecked = false

        holder.viewBinding.checkbox.setOnClickListener{
            onDoneClickListener.onItemClick(position,task)
        }

    }

    override fun getItemCount(): Int {
        return taskList?.size?:0
    }

    fun bindData(tasks:List<Task>) {
        taskList = tasks.toMutableList()
        notifyDataSetChanged()
    }

    fun deleteTask(position: Int) {
        taskList?.removeAt(position)
    }

    lateinit var onDoneClickListener: OnItemClickListener
    lateinit var onMenuClickListener: OnItemClickListener
    fun interface OnItemClickListener{
        fun onItemClick(position:Int,task: Task)
    }

    class ToDoTasksViewHolder(var viewBinding:TodoTaskItemBinding):ViewHolder(viewBinding.root)
}