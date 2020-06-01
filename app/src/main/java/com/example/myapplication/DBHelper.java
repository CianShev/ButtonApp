package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ButtonDB.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.DBEntry.TABLE_NAME + " (" +
                    DBContract.DBEntry._ID + " INTEGER PRIMARY KEY," +
                    DBContract.DBEntry.COLUMN_NAME_USER + " TEXT," +
                    DBContract.DBEntry.COLUMN_NAME_COUNT + " INTEGER," +
                    DBContract.DBEntry.TIMESTAMP + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBContract.DBEntry.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public long insertNew(int count, String user, String dateTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.DBEntry.COLUMN_NAME_USER, user);
        contentValues.put(DBContract.DBEntry.COLUMN_NAME_COUNT, count);
        contentValues.put(DBContract.DBEntry.TIMESTAMP, dateTime);
        long id = db.insert(DBContract.DBEntry.TABLE_NAME, null, contentValues);
        db.close();
        Log.d("Note ID", "insertNew: "+ id);
        return id;
    }

    public int getMostRecent(long id){
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DBContract.DBEntry.TABLE_NAME + " ORDER BY " + DBContract.DBEntry.TIMESTAMP + " DESC LIMIT (1)";
        Cursor cursor = db.rawQuery(selectQuery, null);
        while(cursor.moveToNext()){
            int index;
            index = cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_COUNT );
            result = cursor.getInt(index);
        }
        return result;
    }

    /*
    public int getMostRecent(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DBContract.DBEntry.TABLE_NAME,
                    new String[]{DBContract.DBEntry._ID, DBContract.DBEntry.COLUMN_NAME_COUNT},
                    DBContract.DBEntry._ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        int countValue = cursor.getInt(cursor.getColumnIndex(DBContract.DBEntry._COUNT));
        //int countValue1 = cursor.getInt(cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_COUNT));
        //int countValue2 = cursor.getInt(cursor.getColumnIndex(DBContract.DBEntry._ID));
        //Log.d("getMostRecent", "countValue: " + countValue + " countValue1: "  + " countValue2: " );
        return countValue;
        //return id;
    }
    */

}
