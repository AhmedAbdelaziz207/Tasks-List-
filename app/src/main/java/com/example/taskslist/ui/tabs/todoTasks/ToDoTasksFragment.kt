package com.example.taskslist.ui.tabs.todoTasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import com.example.taskslist.R
import com.example.taskslist.data.database.Task
import com.example.taskslist.databinding.FragmentTodoTasksBinding
import com.example.taskslist.ui.performOptionsMenuClick
import com.example.taskslist.ui.setAttachToRecyclerView
import com.example.taskslist.ui.showTaskInputDialog
import com.example.taskslist.ui.tabs.SharedDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoTasksFragment : Fragment() {
  private lateinit var viewBinding:FragmentTodoTasksBinding
  private lateinit var viewModel: ToDoTasksViewModel
  private lateinit var sharedDataViewModel: SharedDataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTodoTasksBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ToDoTasksViewModel::class.java]
        sharedDataViewModel = ViewModelProvider(requireActivity())[SharedDataViewModel::class.java]
        initViews()
    }

    override fun onStart() {
        super.onStart()
        loadTasks()
    }

    private fun loadTasks() {
         viewModel.getAllTaskLive().observe(viewLifecycleOwner) { tasks ->
             tasksList = tasks
             setAttachToRecyclerView(viewBinding.todoTaskRecycler,tasksList,isChecked)
             Log.e("TAG", tasksList.toString() )
             adapter.bindData(tasks)
         }
    }

    private var adapter = ToDoTasksAdapter()
    private var tasksList = listOf<Task>()

    private fun initViews() {
        viewBinding.todoTaskRecycler.adapter = adapter


        adapter.onMenuClickListener = ToDoTasksAdapter.OnItemClickListener{ position, task->
            performOptionsMenuClick(position,task,
                anchor = viewBinding.todoTaskRecycler[position].findViewById(R.id.menu),
              onEditClickListener =   { task, _ -> editTask(task) }
            ){ task, pos ->
                deleteTask(pos!!, task)
            }
        }


        viewBinding.addTaskBtn.setOnClickListener{
            showTaskInputDialog(null){_,content->
                addTask(content!!)
            }
        }

        adapter.onDoneClickListener = ToDoTasksAdapter.OnItemClickListener{_,task->
            changeTaskState(task)
        }

        observeOnLiveData()

    }

    private var isChecked:Boolean = false
    private fun observeOnLiveData() {
        sharedDataViewModel.switchState.observe(viewLifecycleOwner){
            isChecked = it
        }
    }

    private fun changeTaskState(task: Task) {
        task.isDone = true
        viewModel.updateTask(task)
    }

    private fun editTask( task: Task) {
        showTaskInputDialog(task){task,_->
            viewModel.updateTask(task!!)
        }

    }

    private fun deleteTask(position:Int,task: Task) {
        adapter.deleteTask(position)
        viewModel.deleteTask(task)
    }


    private fun addTask(taskContent: String) {
        val task = Task(taskContent = taskContent, dateTime = System.currentTimeMillis())
        viewModel.addTask(task)
        }

}