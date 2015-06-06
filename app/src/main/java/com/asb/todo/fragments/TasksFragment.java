package com.asb.todo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asb.todo.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class TasksFragment extends Fragment {

    public TasksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }
}
