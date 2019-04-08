package net.unibave.twodolist.controller

import android.content.Context
import net.unibave.twodolist.model.Task
import net.unibave.twodolist.model.TaskLocalDatabaseRepository
import net.unibave.twodolist.model.TaskMemoryRepository
import java.lang.RuntimeException
import java.util.*

class TaskController(context: Context) {

    //    private val repository: TaskMemoryRepository = TaskMemoryRepository()
    private val repository: TaskLocalDatabaseRepository = TaskLocalDatabaseRepository(context)

    fun create(name: String) {
        val task: Task = Task(UUID.randomUUID().toString(), name, Date(), false)
        repository.create(task)
        validateName(task)
    }

    fun validateName(task: Task) {
        if (task.name.isEmpty() || task.name.length > 40) {
            throw RuntimeException("Name is invalid!")
        }
    }

    fun findAll(): MutableList<Task> {
        return repository.findAll()
    }

}