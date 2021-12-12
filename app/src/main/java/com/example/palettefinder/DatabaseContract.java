package com.example.palettefinder;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract(){}

    public static class DataEntry implements BaseColumns{
        public static final String TABLE_NAME = "SavedPalettes";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_COLOR1 = "color1";
        public static final String COLUMN_NAME_COLOR2 = "color2";
        public static final String COLUMN_NAME_COLOR3 = "color3";
        public static final String COLUMN_NAME_COLOR4 = "color4";
        public static final String COLUMN_NAME_COLOR5 = "color5";
    }

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataEntry.TABLE_NAME + " (" +
                    DataEntry._ID + " INTEGER PRIMARY KEY," +
                    DataEntry.COLUMN_NAME_NAME + " TEXT," +
                    DataEntry.COLUMN_NAME_COLOR1 + " TEXT," +
                    DataEntry.COLUMN_NAME_COLOR2 + " TEXT," +
                    DataEntry.COLUMN_NAME_COLOR3 + " TEXT," +
                    DataEntry.COLUMN_NAME_COLOR4 + " TEXT," +
                    DataEntry.COLUMN_NAME_COLOR5 + " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataEntry.TABLE_NAME;
}
