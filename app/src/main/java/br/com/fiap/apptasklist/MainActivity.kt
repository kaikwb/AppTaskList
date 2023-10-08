package br.com.fiap.apptasklist

import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskListView = findViewById<ListView>(R.id.taskListView)
        val taskArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        taskListView.adapter = taskArrayAdapter

        val addTaskBtn = findViewById<FloatingActionButton>(R.id.addTaskBtn)

        addTaskBtn.setOnClickListener {
            openTaskCreateDialog()
        }
    }

    private fun openTaskCreateDialog() {
        val taskDescriptionEdt = EditText(this)
        taskDescriptionEdt.maxLines = 1
        taskDescriptionEdt.inputType = InputType.TYPE_CLASS_TEXT
        taskDescriptionEdt.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val layoutContainer = LinearLayout(this)
        layoutContainer.addView(taskDescriptionEdt)

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(getString(R.string.dialog_title_new_task))
        dialogBuilder.setMessage(getString(R.string.dialog_message_new_task))
        dialogBuilder.setView(layoutContainer)
        dialogBuilder.setPositiveButton(getString(R.string.dialog_accept_button_new_task)) { _, _ ->
            val taskDescription = taskDescriptionEdt.text.toString()
            val taskListView = findViewById<ListView>(R.id.taskListView)

            val taskArrayAdapter = taskListView.adapter as ArrayAdapter<String>
            taskArrayAdapter.add(taskDescription)
        }

        dialogBuilder.setNegativeButton(getString(R.string.dialog_cancel_button_new_task)) { _, _ ->
            Toast.makeText(
                this,
                getString(R.string.toast_message_add_task_cancel), Toast.LENGTH_SHORT
            ).show()
        }

        dialogBuilder.show()
    }
}
