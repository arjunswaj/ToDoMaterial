package com.asb.todo;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.asb.todo.fragments.CompletedTasksFragment;
import com.asb.todo.fragments.CurrentTasksFragment;
import com.asb.todo.fragments.NewTasksFragment;
import com.asb.todo.fragments.PendingTasksFragment;
import com.asb.todo.ui.adapters.TasksPagerAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.ic_logo);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                intent.setAction(TaskActivity.ACTION_ADD_TASK);
                startActivity(intent);
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        TasksPagerAdapter adapter = new TasksPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(NewTasksFragment.newInstance(), getString(R.string.new_tasks));
        adapter.addFragment(CurrentTasksFragment.newInstance(), getString(R.string.current_tasks));
        adapter.addFragment(PendingTasksFragment.newInstance(), getString(R.string.pending_tasks));
        adapter.addFragment(CompletedTasksFragment.newInstance(),
                getString(R.string.completed_tasks));
        viewPager.setAdapter(adapter);
    }
}
