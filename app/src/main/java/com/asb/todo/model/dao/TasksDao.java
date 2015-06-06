package com.asb.todo.model.dao;

import android.database.Cursor;

import com.asb.todo.model.entities.Task;

import java.util.List;

/**
 * Created by arjun on 06/06/15.
 */
public interface TasksDao {

    /**
     * Add a Task
     *
     * @param task task
     */
    void addTask(Task task);

    /**
     * Add Tasks
     *
     * @param tasks list of tasks
     */
    void addTasks(List<Task> tasks);

    /**
     * Get Task by passing id
     *
     * @param id id to be searched
     * @return Task object or null if not found
     */
    Task getTaskById(long id);

    /**
     * Update the task
     *
     * @param task task object with valid id
     * @return old value of task, in case needed for undo
     */
    Task updateTask(Task task);

    /**
     * Updates the list of tasks
     *
     * @param tasks list of tasks to be updated
     * @return list of old task values, in case needed for undo
     */
    List<Task> updateTasks(List<Task> tasks);

    /**
     * Deletes the task
     *
     * @param id id of the task
     * @return Old task object, in case needed for undo
     */
    Task deleteTask(long id);

    /**
     * Deletes the list of tasks
     *
     * @param ids list of ids of tasks to be deleted
     * @return list of old task objects, in case needed for undo
     */
    List<Task> deleteTasks(List<Long> ids);

    /**
     * Gets the cursor which points to tasks that matches the query
     *
     * @param selection     selection query
     * @param selectionArgs arguments
     * @param groupBy       group by argument
     * @param orderBy       order by argument
     * @return Cursor pointing to the tasks
     */
    Cursor getTasks(String selection, String[] selectionArgs, String groupBy, String orderBy);

}