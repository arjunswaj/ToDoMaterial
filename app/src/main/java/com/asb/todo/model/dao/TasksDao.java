package com.asb.todo.model.dao;

import android.database.Cursor;

import com.asb.todo.model.entities.Task;

import java.util.List;

/**
 * Created by arjun on 06/06/15.
 */
public interface TasksDao {

    int FALSE = 0;
    int TRUE = 1;
    String TABLE_NAME = "tasks";
    String COL_ID = "_id";
    String COL_NAME = "name";
    String COL_DESCRIPTION = "description";
    String COL_START_TIME = "start_time";
    String COL_END_TIME = "end_time";
    String COL_IS_COMPLETED = "is_completed";

    String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ID + " integer primary key autoincrement, " +
            COL_NAME + " string not null," +
            COL_DESCRIPTION + " text," +
            COL_START_TIME + " integer not null," +
            COL_END_TIME + " integer not null," +
            COL_IS_COMPLETED + " integer default " + FALSE + " )";

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

    /**
     * Updates the completeness of the Task
     *
     * @param complete whether the task is complete
     * @param id       id to be marked
     * @return id to be returned, in case of undo
     */
    long updateCompletenessOfTask(boolean complete, long id);

    /**
     * Updates the completeness of the Tasks
     *
     * @param complete whether the task is complete
     * @param ids      list of ids to be marked
     * @return list of ids to be returned, in case of undo
     */
    List<Long> updateCompletenessOfTasks(boolean complete, List<Long> ids);

}
