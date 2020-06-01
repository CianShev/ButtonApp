package com.example.myapplication;

import android.provider.BaseColumns;

public class DBContract {


    public static class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "counts";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_COUNT = "count";
        public static final String TIMESTAMP = "timestamp";
    }



}
