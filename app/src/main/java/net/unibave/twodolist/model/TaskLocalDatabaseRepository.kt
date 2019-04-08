package net.unibave.twodolist.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TaskLocalDatabaseRepository(context: Context) {

    var instance: SQLiteDatabase? = null
    var database: Database? = null
    var format: SimpleDateFormat? = null

    init {
        database = Database(context)
        format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }

    fun create(task: Task) {
        val values = ContentValues()
        instance = database?.writableDatabase

        values.put(DatabaseConstants.COLUMN_ID, task.id)
        values.put(DatabaseConstants.COLUMN_NAME, task.name)
        values.put(DatabaseConstants.COLUMN_CREATE_AT, format?.format(task.createdAt))
        values.put(DatabaseConstants.COLUMN_DONE, task.done)

        instance?.insert(DatabaseConstants.TABLE_TASK, null, values)
        instance?.close()

    }

    fun findAll(): MutableList<Task> {
        val tasks: MutableList<Task> = ArrayList()

        val fields: Array<String> = arrayOf(
            DatabaseConstants.COLUMN_ID,
            DatabaseConstants.COLUMN_NAME,
            DatabaseConstants.COLUMN_CREATE_AT,
            DatabaseConstants.COLUMN_DONE
        )

        instance = database?.readableDatabase

        var cursor: Cursor? = instance?.query(
            DatabaseConstants.TABLE_TASK,
            fields,
            null,
            null,
            null,
            null,
            DatabaseConstants.COLUMN_CREATE_AT
        )

        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            var date: Date? = null

            do {
                try {
                    date = format?.parse(cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_CREATE_AT)))
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                var task = Task(
                    cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseConstants.COLUMN_NAME)),
                    date!!,
                    cursor.getInt(cursor.getColumnIndex(DatabaseConstants.COLUMN_DONE)) == 1
                )

                tasks.add(task)
            } while (cursor.moveToNext())

        }

        return tasks
    }
}