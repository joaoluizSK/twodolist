package net.unibave.twodolist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.unibave.twodolist.R
import net.unibave.twodolist.model.Task

class ItemTaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<ItemTaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var task: Task = tasks.get(position)
        holder.taskName.text = task.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var taskName: TextView = itemView.findViewById(R.id.taskName)
    }

}


