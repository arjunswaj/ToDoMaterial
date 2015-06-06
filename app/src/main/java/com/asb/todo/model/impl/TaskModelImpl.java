package com.asb.todo.model.impl;

import android.database.Cursor;

import com.asb.todo.ToDoApplication;
import com.asb.todo.model.TaskModel;
import com.asb.todo.model.dao.TasksDao;
import com.asb.todo.model.entities.Task;

import java.util.List;

/**
 * Created by arjun on 06/06/15.
 */
public class TaskModelImpl implements TaskModel {
    private TasksDao mTasksDao;

    public TaskModelImpl() {
        mTasksDao = ToDoApplication.getInstance().getDaoService().getTasksDao();
    }

    @Override
    public Cursor getNewTasks() {
        return null;
    }

    @Override
    public Cursor getCurrentTasks() {
        return null;
    }

    @Override
    public Cursor getPendingTasks() {
        return null;
    }

    @Override
    public Cursor getCompletedTasks() {
        return null;
    }

    @Override
    public void saveTask(Task task) {

    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public Task deleteTask(Task task) {
        return null;
    }

    @Override
    public List<Task> markTasksComplete(List<Long> ids) {
        return null;
    }

    @Override
    public Task markTaskComplete(long id) {
        return null;
    }

    @Override
    public List<Task> deleteTasks(List<Long> ids) {
        return null;
    }

    @Override
    public Task deleteTask(long id) {
        return null;
    }

    @Override
    public void undoDeletes(List<Task> tasks) {

    }

    @Override
    public void undoDelete(Task task) {

    }

    @Override
    public void undoUpdates(List<Task> tasks) {

    }

    @Override
    public void undoUpdate(Task task) {

    }
}
