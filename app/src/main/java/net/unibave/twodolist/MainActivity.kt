package net.unibave.twodolist

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import net.unibave.twodolist.controller.TaskController
import net.unibave.twodolist.model.Task
import net.unibave.twodolist.view.ItemTaskAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    private var controller: TaskController? = null
    private var taskList: MutableList<Task> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var emptySpaceTasks: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        controller = TaskController(this)
        taskList = controller!!.findAll()

        recyclerView = findViewById(R.id.tasks)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = ItemTaskAdapter(taskList)

        emptySpaceTasks = findViewById(R.id.emptySpaceTasks)
        emptySpaceTasks?.visibility = if (taskList.isEmpty()) View.VISIBLE else View.INVISIBLE

        fab.setOnClickListener(FloatingActionButtonOnClickListener())
    }

    fun createTask(name: String) {
        controller?.create(name)
        refresh()
    }

    fun createDialogTaskName() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val view: View = layoutInflater.inflate(R.layout.dialog_task, null)
        val dialogTaskName: EditText = view.findViewById(R.id.taskName)

        builder.setView(view)
            .setPositiveButton("Add") { _, _ ->
                val name: String = dialogTaskName.text.toString()
                createTask(name)
            }
            .setNegativeButton("Cancel", null)

        builder.create().show()
    }

    fun refresh() {
        taskList.clear()
        taskList.addAll(controller!!.findAll())
        recyclerView?.adapter?.notifyDataSetChanged()
        emptySpaceTasks?.visibility = if (taskList.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    inner class FloatingActionButtonOnClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            createDialogTaskName()
        }
    }

}
