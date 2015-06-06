package com.asb.todo;

import android.app.Application;

import com.asb.todo.model.dao.service.DaoService;
import com.asb.todo.model.dao.service.impl.DaoServiceImpl;

/**
 * Created by arjun on 06/06/15.
 */
public class ToDoApplication extends Application {

    private static ToDoApplication todoApplication;
    private DaoService daoService;

    public static ToDoApplication getInstance() {
        return todoApplication;
    }

    public DaoService getDaoService() {
        return daoService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        todoApplication = this;
        daoService = new DaoServiceImpl(this.getApplicationContext());
    }


}
