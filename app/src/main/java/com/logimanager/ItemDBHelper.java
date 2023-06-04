package com.logimanager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "product.db";
    private static final int DATABASE_VERSION = 1; // 변경된 버전

    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        String createTableQuery = "CREATE TABLE IF NOT EXISTS product (" +
                "_id INTEGER PRIMARY KEY," +
                "id TEXT," +
                "count INTEGER" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @SuppressLint("Range")
    public int getCountForItem(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"count"};
        String selection = "id=?";
        String[] selectionArgs = {id};

        Cursor cursor = db.query("product", projection, selection, selectionArgs, null, null, null);

        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(cursor.getColumnIndex("count"));
            cursor.close();
        }

        return count;
    }

    public void decreaseProductCount(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "UPDATE product SET count = count - 1 WHERE id = ?";
        db.execSQL(updateQuery, new String[]{id});
    }

    public void increaseProductCount(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "UPDATE product SET count = count + 1 WHERE id = ?";
        db.execSQL(updateQuery, new String[]{id});
    }
}