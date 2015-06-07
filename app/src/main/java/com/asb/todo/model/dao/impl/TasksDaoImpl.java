package com.asb.todo.model.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asb.todo.model.dao.TasksDao;
import com.asb.todo.model.entities.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjun on 06/06/15.
 */
public class TasksDaoImpl implements TasksDao {

    private static final String FIND_BY_ID = COL_ID + " =  ?";
    private SQLiteDatabase database;

    public TasksDaoImpl(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void addTask(Task task) {
        ContentValues values = getContentValuesFromTask(task);
        database.insert(TABLE_NAME, null, values);
    }

    @Override
    public void addTasks(List<Task> tasks) {
        database.beginTransaction();
        try {
            for (Task task : tasks) {
                addTask(task);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public Task getTaskById(long id) {
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor =
                database.query(TABLE_NAME, null, FIND_BY_ID, selectionArgs, null, null, null);
        Task task = null;
        if (cursor.moveToFirst()) {
            task = loadTaskFromCursor(cursor);
        }
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        ContentValues values = getContentValuesFromTask(task);
        String[] selectionArgs = {String.valueOf(task.getId())};
        database.update(TABLE_NAME, values, FIND_BY_ID, selectionArgs);
        return task;
    }

    @Override
    public List<Task> updateTasks(List<Task> tasks) {
        database.beginTransaction();
        try {
            for (Task task : tasks) {
                updateTask(task);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return tasks;
    }

    @Override
    public Task deleteTask(long id) {
        Task task = getTaskById(id);
        String[] selectionArgs = {String.valueOf(id)};
        database.delete(TABLE_NAME, FIND_BY_ID, selectionArgs);
        return task;
    }

    @Override
    public List<Task> deleteTasks(List<Long> ids) {
        List<Task> tasks = new ArrayList<>(ids.size());
        database.beginTransaction();
        try {
            for (long id : ids) {
                Task task = getTaskById(id);
                tasks.add(task);
                deleteTask(id);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return tasks;
    }

    @Override
    public Cursor getTasks(String selection, String[] selectionArgs, String groupBy,
                           String orderBy) {
        return database.query(TABLE_NAME, null, selection, selectionArgs, groupBy, null, orderBy);
    }

    @Override
    public long updateCompletenessOfTask(boolean complete, long id) {
        ContentValues values = new ContentValues();
        if (complete) {
            values.put(COL_IS_COMPLETED, TRUE);
        }
        else {
            values.put(COL_IS_COMPLETED, FALSE);
        }
        String[] selectionArgs = {String.valueOf(id)};
        database.update(TABLE_NAME, values, FIND_BY_ID, selectionArgs);
        return id;
    }

    @Override
    public List<Long> updateCompletenessOfTasks(boolean complete, List<Long> ids) {
        database.beginTransaction();
        try {
            for (long id : ids) {
                updateCompletenessOfTask(complete, id);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return ids;
    }

    private ContentValues getContentValuesFromTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, task.getName());
        values.put(COL_DESCRIPTION, task.getDescription());
        values.put(COL_START_TIME, task.getStartTime());
        values.put(COL_END_TIME, task.getEndTime());
        if (task.isCompleted()) {
            values.put(COL_IS_COMPLETED, TRUE);
        }
        else {
            values.put(COL_IS_COMPLETED, FALSE);
        }
        return values;
    }

    private Task loadTaskFromCursor(Cursor cursor) {
        Task task;
        Task.Builder builder = new Task.Builder();
        builder.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)))
                .setName(cursor.getString(cursor.getColumnIndex(COL_NAME)))
                .setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)))
                .setStartTime(cursor.getLong(cursor.getColumnIndex(COL_START_TIME)))
                .setEndTime(cursor.getLong(cursor.getColumnIndex(COL_END_TIME)))
                .setCompleted((TRUE == cursor.getLong(cursor.getColumnIndex(COL_IS_COMPLETED))));
        task = builder.build();
        return task;
    }

}
