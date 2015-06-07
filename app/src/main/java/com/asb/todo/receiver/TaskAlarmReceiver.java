package com.asb.todo.receiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.asb.todo.service.TasksIntentService;

public class TaskAlarmReceiver extends WakefulBroadcastReceiver {

    public static final String ACTION_TASK_START = "com.asb.todo.alarm.ACTION_TASK_START";
    public static final String ACTION_TASK_END = "com.asb.todo.alarm.ACTION_TASK_END";

    public TaskAlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Intent serviceIntent = null;
        switch (action) {
            case ACTION_TASK_START:
                serviceIntent = TasksIntentService.startActionTaskStarted(context);
                break;
            case ACTION_TASK_END:
                serviceIntent = TasksIntentService.startActionTaskEnded(context);
                break;
        }
        if (null != serviceIntent) {
            startWakefulService(context, serviceIntent);
            setResultCode(Activity.RESULT_OK);
        }
    }
}
