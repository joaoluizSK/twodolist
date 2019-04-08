package net.unibave.twodolist.model

class DatabaseConstants {
    companion object {
        @JvmField
        val DB_NAME: String = "DB"
        @JvmField
        val DB_VERSION: Int = 1

        @JvmField
        val TABLE_TASK: String = "TASK"
        @JvmField
        val COLUMN_ID: String = "_id"
        @JvmField
        val COLUMN_NAME: String = "name"
        @JvmField
        val COLUMN_CREATE_AT: String = "create_at"
        @JvmField
        val COLUMN_DONE: String = "done"
    }
}