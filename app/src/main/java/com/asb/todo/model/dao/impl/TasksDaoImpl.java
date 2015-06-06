package com.asb.todo.model.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asb.todo.model.dao.TasksDao;
import com.asb.todo.model.entities.Task;

import java.util.List;

/**
 * Created by arjun on 06/06/15.
 */
public class TasksDaoImpl implements TasksDao {

    private SQLiteDatabase database;

    public TasksDaoImpl(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void addTasks(List<Task> tasks) {

    }

    @Override
    public Task getTaskById(long id) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public List<Task> updateTasks(List<Task> tasks) {
        return null;
    }

    @Override
    public Task deleteTask(long id) {
        return null;
    }

    @Override
    public List<Task> deleteTasks(List<Long> ids) {
        return null;
    }

    @Override
    public Cursor getTasks(String selection, String[] selectionArgs, String groupBy,
                           String orderBy) {
        return null;
    }
}
