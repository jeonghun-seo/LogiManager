package com.logimanager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    TextInputEditText login_id;
    TextInputEditText login_pw;
    TextView sign_up;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_up = findViewById(R.id.sign_up);
        login_btn = findViewById(R.id.login_btn);
        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);

        sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String id = login_id.getText().toString();
                String password = login_pw.getText().toString();
                boolean isMatched = dbHelper.checkCredentials(id, password);
                if (isMatched) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
