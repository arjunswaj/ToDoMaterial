package com.asb.todo.facade.impl;

import android.database.Cursor;

import com.asb.todo.facade.DataLoader;
import com.asb.todo.model.TaskModel;

/**
 * Created by arjun on 06/06/15.
 */
public class PendingTasksDataLoader implements DataLoader {
    private TaskModel taskModel;

    public PendingTasksDataLoader(TaskModel taskModel) {
        this.taskModel = taskModel;
    }

    @Override
    public Cursor getTasksCursor() {
        return taskModel.getPendingTasks();
    }
}
