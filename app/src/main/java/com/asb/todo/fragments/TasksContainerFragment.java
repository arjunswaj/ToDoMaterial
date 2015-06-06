package com.asb.todo.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asb.todo.R;
import com.asb.todo.ui.adapters.TasksPagerAdapter;

public class TasksContainerFragment extends Fragment {

    public static TasksContainerFragment newInstance() {
        return new TasksContainerFragment();
    }

    public TasksContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tasks_container, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setLogo(R.drawable.abc_ic_menu_paste_mtrl_am_alpha);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        TasksPagerAdapter adapter = new TasksPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewTasksFragment.newInstance(), getString(R.string.new_tasks));
        adapter.addFragment(CurrentTasksFragment.newInstance(), getString(R.string.current_tasks));
        adapter.addFragment(PendingTasksFragment.newInstance(), getString(R.string.pending_tasks));
        adapter.addFragment(CompletedTasksFragment.newInstance(),
                getString(R.string.completed_tasks));
        viewPager.setAdapter(adapter);
    }


}
