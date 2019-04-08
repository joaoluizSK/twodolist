package net.unibave.twodolist.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context?) :
    SQLiteOpenHelper(context, DatabaseConstants.DB_NAME, null, DatabaseConstants.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableTask: String = "" +
                "CREATE TABLE ${DatabaseConstants.TABLE_TASK} ( " +
                "${DatabaseConstants.COLUMN_ID} varchar PRIMAY KEY," +
                "${DatabaseConstants.COLUMN_NAME} varchar," +
                "${DatabaseConstants.COLUMN_CREATE_AT} datetime," +
                "${DatabaseConstants.COLUMN_DONE} integer ) "

        db?.execSQL(createTableTask)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}