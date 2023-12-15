package com.example.taskslist.ui.tabs.doneTasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import com.example.taskslist.R
import com.example.taskslist.data.database.Task
import com.example.taskslist.databinding.FragmentDoneTasksBinding
import com.example.taskslist.ui.performOptionsMenuClick
import com.example.taskslist.ui.setAttachToRecyclerView
import com.example.taskslist.ui.showTaskInputDialog
import com.example.taskslist.ui.tabs.SharedDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoneTasksFragment : Fragment() {

    private lateinit var viewBinding: FragmentDoneTasksBinding
    private lateinit var viewModel: DoneTasksViewModel
    private lateinit var sharedDataViewModel: SharedDataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDoneTasksBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DoneTasksViewModel::class.java]
        sharedDataViewModel = ViewModelProvider(requireActivity())[SharedDataViewModel::class.java]
        initViews()
    }

    private var adapter = DoneTasksAdapter()
    private fun initViews() {
        viewBinding.doneTaskRecycler.adapter = adapter

        adapter.onTaskUnselected = DoneTasksAdapter.OnClickTaskListener { task, _ ->
            changeTaskState(task)
        }
        adapter.onMenuClickListener = DoneTasksAdapter.OnClickTaskListener { task, position ->
            performOptionsMenuClick(position = position,
                task = task,
                anchor = viewBinding.doneTaskRecycler[position].findViewById(R.id.menu),
                onEditClickListener = { task, _ ->
                    editTask(task)
                }) { task, pos ->
                deleteTask(pos!!, task)
            }
        }

        observeOnLiveData()
    }

    private var isChecked = false
    private fun observeOnLiveData() {
        sharedDataViewModel.switchState.observe(viewLifecycleOwner) {
            isChecked = it
        }
    }

    private fun changeTaskState(task: Task) {
        task.isDone = false
        viewModel.updateTask(task)
    }

    override fun onStart() {
        super.onStart()
        loadTasks()
    }

    private fun loadTasks() {
        viewModel.getDoneTasks().observe(this) {
            setAttachToRecyclerView(viewBinding.doneTaskRecycler, it, isChecked)
            adapter.bindTasks(it)
        }
    }


    private fun editTask(task: Task) {
        showTaskInputDialog(task) { task, _ ->
            viewModel.updateTask(task!!)
        }

    }

    private fun deleteTask(position: Int, task: Task) {
        adapter.deleteTask(position)
        viewModel.deleteTask(task)
    }


}