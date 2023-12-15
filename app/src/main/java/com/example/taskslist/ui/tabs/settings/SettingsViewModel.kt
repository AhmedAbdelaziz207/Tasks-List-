package com.example.taskslist.ui.tabs.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel:ViewModel() {

    var isSwitchChecked = MutableLiveData<Boolean>()

    fun changeSwitchState(value:Boolean){
        isSwitchChecked.value = value
    }


}