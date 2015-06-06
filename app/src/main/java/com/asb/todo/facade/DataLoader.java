package com.asb.todo.facade;


import android.database.Cursor;

/**
 * Created by arjun on 06/06/15.
 */
public interface DataLoader {

    Cursor getTasksCursor();

}
