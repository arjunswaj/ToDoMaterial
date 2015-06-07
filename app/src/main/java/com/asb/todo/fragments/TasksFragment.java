package com.asb.todo.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.asb.todo.R;
import com.asb.todo.model.TaskModel;
import com.asb.todo.model.impl.TaskModelImpl;
import com.asb.todo.ui.adapters.TasksAdapter;


/**
 * Tasks fragment containing a simple view.
 */
public abstract class TasksFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    protected TaskModel model;
    protected ListView mListView;
    protected CursorAdapter mAdapter;

    public TasksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new TaskModelImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tasks, container, false);
        mListView = (ListView) rootView.findViewById(R.id.tasks_list);
        mAdapter = new TasksAdapter(getActivity(), null);
        mListView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
