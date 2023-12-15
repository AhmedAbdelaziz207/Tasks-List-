package com.example.taskslist.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.taskslist.R
import com.example.taskslist.databinding.ActivityHomeBinding
import com.example.taskslist.ui.tabs.doneTasks.DoneTasksFragment
import com.example.taskslist.ui.tabs.settings.SettingsFragment
import com.example.taskslist.ui.tabs.todoTasks.ToDoTasksFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding :ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        selectBottomNavTab()
    }

    private fun selectBottomNavTab() {
        viewBinding.bottomNavBar.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.todo-> {
                    setToolBarLabel("ToDo")
                    navigateToAnotherFragment(ToDoTasksFragment())
                    true
                }
                R.id.settings ->{
                    setToolBarLabel("Settings")
                    navigateToAnotherFragment(SettingsFragment())
                    true
                }
                R.id.done->{
                    setToolBarLabel("Done")

                    navigateToAnotherFragment(DoneTasksFragment())
                    true
                }
                else->{
                false
            }
            }
        }
        viewBinding.bottomNavBar.selectedItemId = R.id.todo

    }

    private fun setToolBarLabel(label: String) {
        viewBinding.toolbarLabel.text = label

    }

    private fun navigateToAnotherFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}