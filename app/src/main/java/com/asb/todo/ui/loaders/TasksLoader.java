package com.asb.todo.ui.loaders;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.asb.todo.facade.DataLoader;

/**
 * Created by arjun on 06/06/15.
 */
public class TasksLoader extends CursorLoader {

    private DataLoader dataLoader;

    public TasksLoader(Context context, DataLoader dataLoader) {
        super(context);
        this.dataLoader = dataLoader;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = dataLoader.getTasksCursor();
        return cursor;
    }
}
