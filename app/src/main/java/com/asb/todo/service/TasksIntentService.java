package com.asb.todo.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.asb.todo.MainActivity;
import com.asb.todo.R;
import com.asb.todo.model.TaskModel;
import com.asb.todo.model.dao.TasksDao;
import com.asb.todo.model.impl.TaskModelImpl;
import com.asb.todo.receiver.TaskAlarmReceiver;

public class TasksIntentService extends IntentService {

    private static final String ACTION_TASK_STARTED = "com.asb.todo.service.action.TASK_STARTED";
    private static final String ACTION_TASK_ENDED = "com.asb.todo.service.action.TASK_ENDED";
    private static final int TASKS_STARTED_ID = 1000;
    private static final int TASKS_ENDED_ID = 1001;
    public static final String COMMA = ", ";

    private TaskModel model;

    public static Intent startActionTaskStarted(Context context) {
        Intent intent = new Intent(context, TasksIntentService.class);
        intent.setAction(ACTION_TASK_STARTED);
        return intent;
    }

    public static Intent startActionTaskEnded(Context context) {
        Intent intent = new Intent(context, TasksIntentService.class);
        intent.setAction(ACTION_TASK_ENDED);
        return intent;
    }

    public TasksIntentService() {
        super("TasksIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        model = new TaskModelImpl();
        if (intent != null) {
            final String action = intent.getAction();
            switch (action) {
                case ACTION_TASK_STARTED:
                    handleActionTaskStarted();
                    break;
                case ACTION_TASK_ENDED:
                    handleActionTaskEnded();
                    break;
            }
        }
        if (null != intent) {
            TaskAlarmReceiver.completeWakefulIntent(intent);
        }
    }

    private void handleActionTaskStarted() {
        new AsyncTask<Void, Void, NotificationCompat.Builder>() {
            @Override
            protected NotificationCompat.Builder doInBackground(Void... params) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext());
                String title = getString(R.string.new_tasks_notif);
                builder.setSmallIcon(R.drawable.ic_task_notif)
                        .setContentTitle(title);
                NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                Cursor cursor = model.getCurrentTasks();
                String contentText = fillFromCursor(cursor, style);
                style.setBigContentTitle(title);
                builder.setStyle(style);
                builder.setContentText(contentText);
                long[] vibrate = {500, 500};
                builder.setVibrate(vibrate);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setAction(MainActivity.ACTION_SHOW_NEW_TASKS);
                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                        intent, 0);
                builder.setContentIntent(contentIntent);
                builder.setAutoCancel(true);
                return builder;
            }

            @Override
            protected void onPostExecute(NotificationCompat.Builder builder) {
                super.onPostExecute(builder);
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(TASKS_STARTED_ID, builder.build());
            }
        }.execute();

    }

    private void handleActionTaskEnded() {
        new AsyncTask<Void, Void, NotificationCompat.Builder>() {
            @Override
            protected NotificationCompat.Builder doInBackground(Void... params) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext());
                String title = getString(R.string.pending_tasks_notif);
                builder.setSmallIcon(R.drawable.ic_task_notif)
                        .setContentTitle(title);
                NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                Cursor cursor = model.getPendingTasks();
                String contentText = fillFromCursor(cursor, style);
                style.setBigContentTitle(title);
                builder.setContentText(contentText);
                builder.setStyle(style);
                long[] vibrate = {500, 700, 200, 700};
                builder.setVibrate(vibrate);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setAction(MainActivity.ACTION_SHOW_PENDING_TASKS);
                PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                        intent, 0);
                builder.setContentIntent(contentIntent);
                builder.setAutoCancel(true);
                return builder;
            }

            @Override
            protected void onPostExecute(NotificationCompat.Builder builder) {
                super.onPostExecute(builder);
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(TASKS_ENDED_ID, builder.build());
            }
        }.execute();
    }


    private String fillFromCursor(Cursor cursor, NotificationCompat.InboxStyle style) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        String comma = "";
        int count = cursor.getCount();
        if (cursor.moveToFirst()) {
            do {
                counter += 1;
                if (counter < 3) {
                    String name = cursor.getString(cursor.getColumnIndex(TasksDao.COL_NAME));
                    style.addLine(name);
                    sb.append(comma).append(name);
                    comma = COMMA;
                }
                else {
                    break;
                }
            } while (cursor.moveToNext());

            if (3 == count) {
                String name = cursor.getString(cursor.getColumnIndex(TasksDao.COL_NAME));
                style.addLine(name);
                sb.append(comma).append(name);
            }
            else if (count > 3) {
                int remainingTasks = count - counter + 1;
                String summary = getString(R.string.more_tasks, remainingTasks);
                style.addLine(summary);
                sb.append(comma).append(summary);
            }

        }
        return sb.toString();
    }
}
