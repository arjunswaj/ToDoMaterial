package com.asb.todo.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.asb.todo.R;
import com.asb.todo.model.dao.TasksDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by arjun on 06/06/15.
 */
public class TasksAdapter extends CursorAdapter {

    private static class ViewHolder {
        TextView name;
        TextView description;
        TextView startTime;
        TextView endTime;
        CheckBox complete;
    }

    private DateFormat dateFormatter;

    public TasksAdapter(Context context, Cursor c) {
        super(context, c, 0);
        dateFormatter =
                new SimpleDateFormat(context.getString(R.string.task_timestamp_format),
                        Locale.ENGLISH);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_item, parent, false);

        ViewHolder holder = new ViewHolder();
        holder.name = (TextView) view.findViewById(R.id.task_name);
        holder.description = (TextView) view.findViewById(R.id.task_description);
        holder.startTime = (TextView) view.findViewById(R.id.task_start_time);
        holder.endTime = (TextView) view.findViewById(R.id.task_end_time);
        holder.complete = (CheckBox) view.findViewById(R.id.complete);

        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String name = cursor.getString(cursor.getColumnIndex(TasksDao.COL_NAME));
        String description = cursor.getString(cursor.getColumnIndex(TasksDao.COL_DESCRIPTION));
        long startTimeInMs = cursor.getLong(cursor.getColumnIndex(TasksDao.COL_START_TIME));
        long endTimeInMs = cursor.getLong(cursor.getColumnIndex(TasksDao.COL_END_TIME));
        boolean completed =
                (TasksDao.TRUE == cursor.getLong(cursor.getColumnIndex(TasksDao.COL_IS_COMPLETED)));


        Date startDate = new Date(startTimeInMs);
        String startTime = dateFormatter.format(startDate);
        Date endDate = new Date(endTimeInMs);
        String endTime = dateFormatter.format(endDate);

        holder.name.setText(name);
        holder.description.setText(description);
        holder.startTime.setText(startTime);
        holder.endTime.setText(endTime);
        holder.complete.setChecked(completed);
    }
}
