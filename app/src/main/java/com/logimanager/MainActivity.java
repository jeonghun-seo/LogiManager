package com.logimanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    EditText login_id;
    EditText login_pw;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = login_id.getText().toString();
                String password = login_pw.getText().toString();

                if (validateCredentials(username, password)) {
                    // 로그인 성공
                    Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                } else {
                    // 로그인 실패
                    Toast.makeText(MainActivity.this, "아이디 또는 패스워드가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // 데이터베이스에서 입력된 아이디와 패스워드 대조
        String[] columns = {"login_id"};
        String selection = "login_id = ? AND login_pw = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }
}