package com.example.taskslist.ui.tabs.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.taskslist.databinding.FragmentSettingsBinding
import com.example.taskslist.ui.tabs.SharedDataViewModel

class SettingsFragment : Fragment() {
   private lateinit var viewBinding:FragmentSettingsBinding
   private lateinit var viewModel: SettingsViewModel
   private lateinit var sharedDataViewModel: SharedDataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSettingsBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        sharedDataViewModel = ViewModelProvider(requireActivity())[SharedDataViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        // Get a reference to SharedPreferences
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Set the initial state of the Switch based on the stored value
        viewBinding.recorderSwitch.isChecked = sharedPreferences.getBoolean("switch_state", false)

        // Set a listener for the Switch
        viewBinding.recorderSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save the state in SharedPreferences when the Switch state changes
            with(sharedPreferences.edit()) {
                putBoolean("switch_state", isChecked)
                apply()
            }
            val switchState = sharedPreferences.getBoolean("switch_state", false)

            updateViewModelWithSwitchState(isChecked)
        }
        updateViewModelWithSwitchState(viewBinding.recorderSwitch.isChecked)

        observeOnLiveData()
    }
    private fun updateViewModelWithSwitchState(isChecked: Boolean) {
        if (isChecked) {
            viewModel.changeSwitchState(true)
        } else {
            viewModel.changeSwitchState(false)
        }
    }
    private fun observeOnLiveData() {
        viewModel.isSwitchChecked.observe(viewLifecycleOwner){isChecked->
            sharedDataViewModel.setSwitchState(isChecked)
        }
    }

}