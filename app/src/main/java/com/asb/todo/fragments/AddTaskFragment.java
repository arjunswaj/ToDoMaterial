package com.asb.todo.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.asb.todo.R;
import com.asb.todo.model.TaskModel;
import com.asb.todo.model.entities.Task;
import com.asb.todo.model.impl.TaskModelImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A add tasks fragment containing a simple view.
 */
public class AddTaskFragment extends Fragment {

    public static final String TAG = AddTaskFragment.class.getSimpleName();
    private static final long FIVE_MINUTES = 5 * 60 * 1000;
    private static final long THIRTY_MINUTES = 30 * 60 * 1000;
    private TextInputLayout taskName;
    private TextInputLayout taskDescription;
    private EditText name;
    private EditText description;
    private TextView startTime;
    private TextView startDate;
    private TextView endTime;
    private TextView endDate;
    private DateFormat timeFormatter;
    private DateFormat dateFormatter;
    private TaskModel model;
    private Date start;
    private Date end;

    private Listener mListener;

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }

    public AddTaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        String timeFormat = getString(R.string.task_time_format);
        String dateFormat = getString(R.string.task_date_format);
        timeFormatter = new SimpleDateFormat(timeFormat, Locale.ENGLISH);
        dateFormatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);

        View rootView = inflater.inflate(R.layout.fragment_add_task, container, false);
        taskName = (TextInputLayout) rootView.findViewById(R.id.task_name);
        taskDescription = (TextInputLayout) rootView.findViewById(R.id.task_description);
        name = (EditText) rootView.findViewById(R.id.name);
        description = (EditText) rootView.findViewById(R.id.description);
        startTime = (TextView) rootView.findViewById(R.id.task_start_time);
        startDate = (TextView) rootView.findViewById(R.id.task_start_date);
        endTime = (TextView) rootView.findViewById(R.id.task_time);
        endDate = (TextView) rootView.findViewById(R.id.task_end_date);

        long now = System.currentTimeMillis() + FIVE_MINUTES;
        start = new Date(now);
        long later = now + THIRTY_MINUTES;
        end = new Date(later);

        model = new TaskModelImpl();
        setTimeAndDate();
        setTimeAndDateListeners();
        return rootView;
    }

    private void setTimeAndDate() {
        startTime.setText(timeFormatter.format(start));
        startDate.setText(dateFormatter.format(start));
        endTime.setText(timeFormatter.format(end));
        endDate.setText(dateFormatter.format(end));
    }

    private void setTimeAndDateListeners() {
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(start);
                TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.setTime(start);
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                start = calendar.getTime();
                                long later = start.getTime() + THIRTY_MINUTES;
                                end.setTime(later);
                                setTimeAndDate();
                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(start);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                calendar.setTime(start);
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, monthOfYear);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                start = calendar.getTime();
                                long later = start.getTime() + THIRTY_MINUTES;
                                end.setTime(later);
                                setTimeAndDate();
                            }
                        },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(end);
                TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.setTime(start);
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                end = calendar.getTime();
                                if (start.getTime() >= end.getTime()) {
                                    long earlier = end.getTime() - THIRTY_MINUTES;
                                    start.setTime(earlier);
                                }
                                setTimeAndDate();
                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(end);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                calendar.setTime(start);
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, monthOfYear);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                end = calendar.getTime();
                                if (start.getTime() >= end.getTime()) {
                                    long earlier = end.getTime() - THIRTY_MINUTES;
                                    start.setTime(earlier);
                                }
                                setTimeAndDate();
                            }
                        },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_task_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                if (taskIsValid()) {
                    saveTheTask();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean taskIsValid() {
        boolean valid = true;
        if (TextUtils.isEmpty(name.getText())) {
            taskName.setError(getString(R.string.task_name_cannot_be_empty));
            valid = false;
        }
        return valid;
    }

    private void saveTheTask() {
        Task task = new Task.Builder()
                .setName(name.getText().toString())
                .setDescription(description.getText().toString())
                .setStartTime(start.getTime())
                .setEndTime(end.getTime())
                .setCompleted(false)
                .build();
        model.saveTask(task);
        hideKeyBoard();
        mListener.onSaveComplete();
    }

    private void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(name.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(description.getWindowToken(), 0);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (Listener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Listener {
        void onSaveComplete();
    }

}
