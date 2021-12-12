package com.example.palettefinder;

import static com.example.palettefinder.DatabaseContract.SQL_CREATE_ENTRIES;
import static com.example.palettefinder.DatabaseContract.SQL_DELETE_ENTRIES;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_FILE_NAME = "mydatabase";
    private static final String DATABASE_TABLE_NAME = "mydatabase";
    private static final String PKEY = "id";
    private static final String NAME = "name";
    private static final String COL1 = "color1";
    private static final String COL2 = "color2";
    private static final String COL3 = "color3";
    private static final String COL4 = "color4";
    private static final String COL5 = "color5";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                PKEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT," +
                COL1 + " TEXT," +
                COL2 + " TEXT," +
                COL3 + " TEXT," +
                COL4 + " TEXT," +
                COL5 + " TEXT);";
        db.execSQL(DATABASE_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
            onCreate(db);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertData(ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.insertOrThrow(DATABASE_TABLE_NAME,null,values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @SuppressLint("Range")
    public void readData() {
        Log.i("JFL", "Reading database...");
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("JFL", "Number of entries: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(COL1)));
            } while (cursor.moveToNext());
        }
    }

    @SuppressLint("Range")
    public void printData(ArrayAdapter<String> tableau) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                tableau.add(cursor.getString(cursor.getColumnIndex(NAME))+","+cursor.getString(cursor.getColumnIndex(COL1))+","+cursor.getString(cursor.getColumnIndex(COL2))+","+cursor.getString(cursor.getColumnIndex(COL3))+","+cursor.getString(cursor.getColumnIndex(COL4))+","+cursor.getString(cursor.getColumnIndex(COL5)));
            } while (cursor.moveToNext());
        }
    }
}
