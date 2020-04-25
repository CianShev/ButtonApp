package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app_database";
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_NAME = "userID";
    public static final String USER_COLUMN_CLICKTOTAL = "clickTotal";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (" +
                USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COLUMN_NAME + " TEXT, " +
                USER_COLUMN_CLICKTOTAL + " INTEGER" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void saveToDB() {
        SQLiteDatabase database = new DBHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.USER_COLUMN_NAME, activityBinding.nameEditText.getText().toString());
        values.put(DBHelper.USER_COLUMN_AGE, activityBinding.ageEditText.getText().toString());
        values.put(DBHelper.USER_COLUMN_GENDER, activityBinding.genderEditText.getText().toString());
        long newRowId = database.insert(SampleSQLiteDBHelper.PERSON_TABLE_NAME, null, values);

        Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
    }

}
