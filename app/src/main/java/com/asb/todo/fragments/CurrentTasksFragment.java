package com.asb.todo.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.asb.todo.facade.DataLoader;
import com.asb.todo.facade.impl.CurrentTasksDataLoader;
import com.asb.todo.ui.loaders.TasksLoader;

/**
 * Created by arjun on 06/06/15.
 */
public class CurrentTasksFragment extends TasksFragment {

    public static final String TAG = CurrentTasksFragment.class.getSimpleName();
    private static final int LOADER_ID = 1001;

    public static CurrentTasksFragment newInstance() {
        return new CurrentTasksFragment();
    }

    public CurrentTasksFragment() {
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
        DataLoader dataLoader = new CurrentTasksDataLoader(model);
        return new TasksLoader(getActivity().getApplicationContext(), dataLoader);
    }
}
