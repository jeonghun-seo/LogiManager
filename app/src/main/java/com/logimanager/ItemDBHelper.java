package com.logimanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 2; // 변경된 버전

    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table
        String createTableQuery = "CREATE TABLE products (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT," +
                "count INTEGER" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
        String dropTableQuery = "DROP TABLE IF EXISTS products";
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void insertProduct(String id, int count) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("count", count);

        db.insert("products", null, values);
        db.close();
    }

    public void decreaseProductCount(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "UPDATE products SET count = count - 1 WHERE id = ?";
        db.execSQL(updateQuery, new String[]{id});

        db.close();
    }
}
