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
    private static final String TASKS_ORDER_BY =
            TasksDao.COL_START_TIME + " ASC, " + TasksDao.COL_END_TIME + " ASC";
    private static final String NEW_TASKS_SELECTION =
            TasksDao.COL_START_TIME + " > ? AND " + TasksDao.COL_IS_COMPLETED + " = ?";
    private static final String CURRENT_TASKS_SELECTION =
            TasksDao.COL_START_TIME + " <= ? AND " + TasksDao.COL_END_TIME + " >= ? AND " +
                    TasksDao.COL_IS_COMPLETED + " = ?";
    private static final String PENDING_TASKS_SELECTION =
            TasksDao.COL_END_TIME + " < ? AND " + TasksDao.COL_IS_COMPLETED + " = ?";
    private static final String COMPLETED_TASKS_SELECTION = TasksDao.COL_IS_COMPLETED + " = ?";
    private static final String COMPLETED_TASKS_ORDER_BY =
            TasksDao.COL_START_TIME + " DESC, " + TasksDao.COL_END_TIME + " DESC";

    private TasksDao mTasksDao;

    public TaskModelImpl() {
        mTasksDao = ToDoApplication.getInstance().getDaoService().getTasksDao();
    }

    @Override
    public Cursor getNewTasks() {
        long now = System.currentTimeMillis();
        String[] selectionArgs =
                {String.valueOf(now), String.valueOf(TasksDao.FALSE)};
        return mTasksDao.getTasks(NEW_TASKS_SELECTION, selectionArgs, null, TASKS_ORDER_BY);
    }

    @Override
    public Cursor getCurrentTasks() {
        long now = System.currentTimeMillis();
        String[] selectionArgs =
                {String.valueOf(now), String.valueOf(now), String.valueOf(TasksDao.FALSE)};
        return mTasksDao.getTasks(CURRENT_TASKS_SELECTION, selectionArgs, null, TASKS_ORDER_BY);
    }

    @Override
    public Cursor getPendingTasks() {
        long now = System.currentTimeMillis();
        String[] selectionArgs =
                {String.valueOf(now), String.valueOf(TasksDao.FALSE)};
        return mTasksDao.getTasks(PENDING_TASKS_SELECTION, selectionArgs, null, TASKS_ORDER_BY);
    }

    @Override
    public Cursor getCompletedTasks() {
        String[] selectionArgs = {String.valueOf(TasksDao.TRUE)};
        return mTasksDao
                .getTasks(COMPLETED_TASKS_SELECTION, selectionArgs, null, COMPLETED_TASKS_ORDER_BY);
    }

    @Override
    public void saveTask(Task task) {
        mTasksDao.addTask(task);
        // TODO: set alarm
    }

    @Override
    public Task updateTask(Task task) {
        Task rTask = mTasksDao.updateTask(task);
        // TODO: update alarm
        return rTask;
    }

    @Override
    public List<Long> markTasksComplete(List<Long> ids) {
        return mTasksDao.updateCompletenessOfTasks(true, ids);
    }

    @Override
    public long markTaskComplete(long id) {
        return mTasksDao.updateCompletenessOfTask(true, id);
    }

    @Override
    public List<Long> markTasksIncomplete(List<Long> ids) {
        return mTasksDao.updateCompletenessOfTasks(false, ids);
    }

    @Override
    public long markTaskIncomplete(long id) {
        return mTasksDao.updateCompletenessOfTask(false, id);
    }

    @Override
    public List<Task> deleteTasks(List<Long> ids) {
        return mTasksDao.deleteTasks(ids);
    }

    @Override
    public Task deleteTask(long id) {
        return mTasksDao.deleteTask(id);
    }

    @Override
    public void undoDeletes(List<Task> tasks) {
        mTasksDao.addTasks(tasks);
        // TODO: set alarms
    }

    @Override
    public void undoDelete(Task task) {
        mTasksDao.addTask(task);
        // TODO: set alarm
    }

    @Override
    public void undoUpdates(List<Task> tasks) {
        mTasksDao.updateTasks(tasks);
    }

    @Override
    public void undoUpdate(Task task) {
        mTasksDao.updateTask(task);
    }

    @Override
    public void undoMarkTaskComplete(long id) {
        mTasksDao.updateCompletenessOfTask(false, id);
    }

    @Override
    public void undoMarkTasksComplete(List<Long> ids) {
        mTasksDao.updateCompletenessOfTasks(false, ids);
    }

    @Override
    public void undoMarkTaskIncomplete(long id) {
        mTasksDao.updateCompletenessOfTask(true, id);
    }

    @Override
    public void undoMarkTasksIncomplete(List<Long> ids) {
        mTasksDao.updateCompletenessOfTasks(true, ids);
    }
}
