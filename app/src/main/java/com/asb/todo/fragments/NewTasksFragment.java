package com.asb.todo.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.asb.todo.facade.DataLoader;
import com.asb.todo.facade.impl.NewTasksDataLoader;
import com.asb.todo.ui.loaders.TasksLoader;

/**
 * Created by arjun on 06/06/15.
 */
public class NewTasksFragment extends TasksFragment {

    public static final String TAG = NewTasksFragment.class.getSimpleName();
    private static final int LOADER_ID = 1000;

    public static NewTasksFragment newInstance() {
        return new NewTasksFragment();
    }

    public NewTasksFragment() {
        super();
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }

    protected void reloadData() {
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        DataLoader dataLoader = new NewTasksDataLoader(model);
        return new TasksLoader(getActivity().getApplicationContext(), dataLoader);
    }
}
