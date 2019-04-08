package net.unibave.twodolist.model

import java.util.ArrayList

class TaskMemoryRepository {

    private var tasks: MutableList<Task> = ArrayList()

    fun create(task: Task) {
        tasks.add(task)
    }

    fun findAll(): MutableList<Task> {
        return tasks
    }

}