package com.example.taskslist.ui.tabs.addTask

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskslist.R
import com.example.taskslist.data.database.Task
import com.example.taskslist.databinding.AddTaskDialogBinding
import com.example.taskslist.databinding.FragmentAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : DialogFragment() {
    private lateinit var viewBinding:FragmentAddTaskBinding
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAddTaskBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.buttonCancel.setOnClickListener{
            dismiss()
        }
    }

    private fun addTask(taskContent: String) {
        if (taskContent.isBlank()){
            val task = Task(taskContent = taskContent)
            viewModel.addTask(task)
        }
    }


}