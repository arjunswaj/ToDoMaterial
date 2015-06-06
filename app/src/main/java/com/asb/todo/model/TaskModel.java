package com.asb.todo.model;

import android.database.Cursor;

import com.asb.todo.model.entities.Task;

import java.util.List;

/**
 * Created by arjun on 06/06/15.
 */
public interface TaskModel {

    /**
     * Gets all new tasks
     *
     * @return Cursor of the new Tasks
     */
    Cursor getNewTasks();

    /**
     * Gets all new tasks
     *
     * @return Cursor of the current Tasks
     */
    Cursor getCurrentTasks();

    /**
     * Gets all new tasks
     *
     * @return Cursor of the pending Tasks
     */
    Cursor getPendingTasks();

    /**
     * Gets all new tasks
     *
     * @return Cursor of the completed Tasks
     */
    Cursor getCompletedTasks();

    /**
     * Saves the task
     *
     * @param task task to be saved
     */
    void saveTask(Task task);

    /**
     * Updates the task
     *
     * @param task task to be updated
     * @return Old state of the task, in case required for undo
     */
    Task updateTask(Task task);

    /**
     * Deletes the task
     *
     * @param task task to be deleted
     * @return Old state of the task, in case required for undo
     */
    Task deleteTask(Task task);

    /**
     * Marks the tasks complete and returns the old state, in case required for undo
     *
     * @param ids Ids to be updated
     * @return Old state of the task
     */
    List<Task> markTasksComplete(List<Long> ids);

    /**
     * Marks the task complete and returns the old state, in case required for undo
     *
     * @param id Id to be updated
     * @return Old state of the task
     */
    Task markTaskComplete(long id);

    /**
     * Deletes the tasks and returns the old state, in case required for undo
     *
     * @param ids Ids to be updated
     * @return Old state of the task
     */
    List<Task> deleteTasks(List<Long> ids);


    /**
     * Delete the task and returns the old state, in case required for undo
     *
     * @param id Id to be updated
     * @return Old state of the task
     */
    Task deleteTask(long id);

    /**
     * Undo the deletes
     * @param tasks tasks to be re-added
     */
    void undoDeletes(List<Task> tasks);

    /**
     * Undo the delete
     * @param task task to be re-added
     */
    void undoDelete(Task task);

    /**
     * Undo the updates
     * @param tasks tasks to be reverted
     */
    void undoUpdates(List<Task> tasks);

    /**
     * Undo the update
     * @param task task to be reverted
     */
    void undoUpdate(Task task);

}
