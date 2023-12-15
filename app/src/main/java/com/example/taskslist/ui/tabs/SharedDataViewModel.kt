package com.example.taskslist.ui.tabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedDataViewModel:ViewModel() {
    val switchState = MutableLiveData<Boolean>()

    fun setSwitchState(isChecked:Boolean){
        switchState.value = isChecked
    }
}