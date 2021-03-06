package com.asb.todo.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.asb.todo.facade.DataLoader;
import com.asb.todo.facade.impl.CompletedTasksDataLoader;
import com.asb.todo.ui.loaders.TasksLoader;

/**
 * Created by arjun on 06/06/15.
 */
public class CompletedTasksFragment extends TasksFragment {

    public static final String TAG = CompletedTasksFragment.class.getSimpleName();
    private static final int LOADER_ID = 1003;

    public static CompletedTasksFragment newInstance() {
        return new CompletedTasksFragment();
    }

    public CompletedTasksFragment() {
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
        DataLoader dataLoader = new CompletedTasksDataLoader(model);
        return new TasksLoader(getActivity().getApplicationContext(), dataLoader);
    }
}
