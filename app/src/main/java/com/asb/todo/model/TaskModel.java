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
     * Marks the tasks complete and returns the ids, in case required for undo
     *
     * @param ids Ids to be marked complete
     * @return Ids of the tasks marked complete
     */
    List<Long> markTasksComplete(List<Long> ids);

    /**
     * Marks the task complete and returns the id, in case required for undo
     *
     * @param id Id to be marked complete
     * @return Id of the tasks marked complete
     */
    long markTaskComplete(long id);

    /**
     * Marks the tasks complete and returns the ids, in case required for undo
     *
     * @param ids Ids to be marked complete
     * @return Ids of the tasks marked complete
     */
    List<Long> markTasksIncomplete(List<Long> ids);

    /**
     * Marks the task incomplete and returns the id, in case required for undo
     *
     * @param id Id to be marked complete
     * @return Id of the tasks marked complete
     */
    long markTaskIncomplete(long id);

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
     *
     * @param tasks tasks to be re-added
     */
    void undoDeletes(List<Task> tasks);

    /**
     * Undo the delete
     *
     * @param task task to be re-added
     */
    void undoDelete(Task task);

    /**
     * Undo the updates
     *
     * @param tasks tasks to be reverted
     */
    void undoUpdates(List<Task> tasks);

    /**
     * Undo the update
     *
     * @param task task to be reverted
     */
    void undoUpdate(Task task);

    /**
     * Undo marking task complete
     *
     * @param id id of the task
     */
    void undoMarkTaskComplete(long id);

    /**
     * Undo marking tasks complete
     *
     * @param ids ids of the tasks
     */
    void undoMarkTasksComplete(List<Long> ids);

    /**
     * Undo marking task incomplete
     *
     * @param id id of the task
     */
    void undoMarkTaskIncomplete(long id);

    /**
     * Undo marking task incomplete
     *
     * @param ids ids of the task
     */
    void undoMarkTasksIncomplete(List<Long> ids);

}
