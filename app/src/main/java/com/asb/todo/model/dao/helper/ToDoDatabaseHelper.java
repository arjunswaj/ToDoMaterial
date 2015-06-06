package com.asb.todo.model.dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.asb.todo.model.dao.TasksDao;

/**
 * Created by arjun on 06/06/15.
 */
public class ToDoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.sqlite";

    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TASKS_TABLE = "CREATE TABLE " + TasksDao.TABLE_NAME + " (" +
            TasksDao.COL_ID + " integer primary key autoincrement, " +
            TasksDao.COL_NAME + " string not null," +
            TasksDao.COL_DESCRIPTION + " text," +
            TasksDao.COL_START_TIME + " integer not null," +
            TasksDao.COL_END_TIME + " integer not null," +
            TasksDao.COL_IS_COMPLETED + " integer default 0 )";


    public ToDoDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
