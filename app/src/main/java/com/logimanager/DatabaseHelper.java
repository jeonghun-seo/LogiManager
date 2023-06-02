package com.logimanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userdata.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스 테이블 생성 쿼리 실행
        db.execSQL("CREATE TABLE usertable (user_id TEXT PRIMARY KEY, user_pw TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 데이터베이스 스키마 업그레이드 로직
        // 필요한 경우 테이블을 삭제하고 다시 생성할 수 있습니다.
        db.execSQL("DROP TABLE IF EXISTS usertable");
        onCreate(db);
    }
}
