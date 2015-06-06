package com.asb.todo.model.dao.service.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.asb.todo.model.dao.TasksDao;
import com.asb.todo.model.dao.helper.ToDoDatabaseHelper;
import com.asb.todo.model.dao.impl.TasksDaoImpl;
import com.asb.todo.model.dao.service.DaoService;

/**
 * Created by arjun on 06/06/15.
 */
public class DaoServiceImpl implements DaoService {
    private ToDoDatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private TasksDao tasksDao;

    public DaoServiceImpl(Context context) {
        dbHelper = new ToDoDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public TasksDao getTasksDao() {
        if (null == tasksDao) {
            tasksDao = new TasksDaoImpl(database);
        }
        return tasksDao;
    }
}
