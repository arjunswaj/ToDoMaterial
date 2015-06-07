package com.asb.todo.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.asb.todo.facade.DataLoader;
import com.asb.todo.facade.impl.PendingTasksDataLoader;
import com.asb.todo.ui.adapters.TasksAdapter;
import com.asb.todo.ui.loaders.TasksLoader;

/**
 * Created by arjun on 06/06/15.
 */
public class PendingTasksFragment extends TasksFragment {

    public static final String TAG = PendingTasksFragment.class.getSimpleName();
    private static final int LOADER_ID = 1002;

    public static PendingTasksFragment newInstance() {
        return new PendingTasksFragment();
    }

    public PendingTasksFragment() {
        super();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new TasksAdapter(getActivity(), null);
        mListView.setAdapter(mAdapter);
        reloadData();
    }

    private void reloadData() {
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        DataLoader dataLoader = new PendingTasksDataLoader(model);
        return new TasksLoader(getActivity().getApplicationContext(), dataLoader);
    }
}
