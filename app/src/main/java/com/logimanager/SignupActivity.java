package com.logimanager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    EditText signup_id;
    EditText signup_pw;
    EditText signup_phone;
    Button signup_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        signup_id = findViewById(R.id.signup_id);
        signup_pw = findViewById(R.id.signup_pw);
        signup_phone = findViewById(R.id.signup_phone);
        signup_btn = findViewById(R.id.signup_btn);
        values.put("user_id", "");
        values.put("user_pw", "");

        db.insert("usertable", null, values);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
