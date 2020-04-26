package com.example.myapplication;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract(){

    }

    public static class DBEntry implements BaseColumns{
        public static final String TABLE_NAME = "counts";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_COUNT = "count";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBEntry.TABLE_NAME + " (" +
                    DBEntry._ID + " INTEGER PRIMARY KEY," +
                    DBEntry.COLUMN_NAME_USER + " TEXT," +
                    DBEntry.COLUMN_NAME_COUNT + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBEntry.TABLE_NAME;

}
