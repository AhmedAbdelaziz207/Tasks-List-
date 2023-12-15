package com.example.taskslist.ui.tabs.doneTasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskslist.data.database.Task
import com.example.taskslist.databinding.DoneTaskItemBinding

class DoneTasksAdapter(private var taskList : MutableList<Task>? = null): Adapter<DoneTasksAdapter.DoneTasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoneTasksViewHolder {
        val viewBinding = DoneTaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DoneTasksViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DoneTasksViewHolder, position: Int) {
       val task = taskList!![position]
        holder.viewBinding.taskContent.text = task.taskContent
        holder.viewBinding.checkbox.isChecked = true
        holder.viewBinding.checkbox.setOnClickListener{
            onTaskUnselected.onClick(task,position)
        }
        holder.viewBinding.menu.setOnClickListener{
            onMenuClickListener.onClick(task,position)
        }
    }

    override fun getItemCount(): Int {
        return taskList?.size?:0
    }

    fun bindTasks(tasks: List<Task>?) {
        taskList = tasks?.toMutableList()
        notifyDataSetChanged()
    }

    fun deleteTask(position: Int) {
        taskList?.removeAt(position)
        notifyItemRemoved(position)
    }

    lateinit var onTaskUnselected:OnClickTaskListener
    lateinit var onMenuClickListener:OnClickTaskListener
    fun interface OnClickTaskListener{
        fun onClick(task:Task, position: Int)
    }
    class DoneTasksViewHolder(val viewBinding: DoneTaskItemBinding):ViewHolder(viewBinding.root)
}