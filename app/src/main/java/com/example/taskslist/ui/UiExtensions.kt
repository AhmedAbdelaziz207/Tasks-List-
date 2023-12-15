package com.example.taskslist.ui

import android.app.AlertDialog
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.taskslist.R
import com.example.taskslist.data.database.Task
import com.example.taskslist.databinding.AddTaskDialogBinding
import java.util.Collections

fun Fragment.showTaskInputDialog(task: Task?= null,onSaveClickListener: OnButtonClickListener) {
    val builder = AlertDialog.Builder(context)
    val dialogViewBinding = AddTaskDialogBinding.inflate(layoutInflater)
    builder.setView(dialogViewBinding.root)
    val dialog = builder.create()

    if (task != null ){
        dialogViewBinding.editTextTask.setText(task.taskContent)
    }

    dialogViewBinding.buttonSave.setOnClickListener {
        if (task == null){
            val taskContent = dialogViewBinding.editTextTask.text.toString()
            onSaveClickListener.onButtonClicked(null,taskContent)

        }else{
            val content =   dialogViewBinding.editTextTask.text.toString()
            task.taskContent = content

            onSaveClickListener.onButtonClicked(task,null)

        }

        dialog.dismiss()
    }

    dialogViewBinding.buttonCancel.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}

 fun Fragment.performOptionsMenuClick(
     position: Int,
     task: Task,
     anchor:View,
     onEditClickListener: OnOptionClickListener,
     onDeleteClickListener: OnOptionClickListener,
    ) {
    val popupMenu = PopupMenu(context ,anchor)

    popupMenu.inflate(R.menu.todo_option_menu)
    popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.delete -> {
                    onDeleteClickListener.onOptionClicked(task,position)
                    return true
                }
                R.id.edit -> {
                    onEditClickListener.onOptionClicked(task,null)
                    return true
                }
            }
            return false
        }
    })
    popupMenu.show()

}

fun Fragment.setAttachToRecyclerView(recyclerView: View, itemList:List<Task>, isCheck:Boolean){
    val onTouchHelper = object : ItemTouchHelper
    .SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,0){
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {

            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition

            Collections.swap(itemList, fromPosition, toPosition)
            recyclerView.adapter?.notifyItemMoved(fromPosition,toPosition)
            return false
        }
    }

    if (isCheck){
        ItemTouchHelper(onTouchHelper).apply {
            attachToRecyclerView(recyclerView as RecyclerView)
        }
    }else{
        ItemTouchHelper(onTouchHelper).apply {
            attachToRecyclerView(null)
        }
    }

}
fun interface OnOptionClickListener{
    fun onOptionClicked(task: Task,position:Int?)
}

fun interface OnButtonClickListener{
    fun onButtonClicked(task: Task?,content:String?)
}
