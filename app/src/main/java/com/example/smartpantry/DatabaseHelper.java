import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smartpantry.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

   package com.example.smartpantry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "smartpantry.db";
        private static final int DATABASE_VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE pantry (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "quantity INTEGER, " +
                    "unit TEXT" +
                    ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS pantry");
            onCreate(db);
        }

        public List<PantryItem> getAllItems() {
            List<PantryItem> items = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT name FROM pantry", null);

            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(0);
                    items.add(new PantryItem(name));
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return items;
        }
    }
