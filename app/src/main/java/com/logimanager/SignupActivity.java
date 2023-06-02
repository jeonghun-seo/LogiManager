package com.logimanager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    TextInputEditText signup_id;
    TextInputEditText signup_pw;
    TextInputEditText signup_phone;

    Button signup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup_btn = findViewById(R.id.signup_btn);
        signup_id = findViewById(R.id.signup_id);
        signup_pw = findViewById(R.id.signup_pw);
        signup_phone = findViewById(R.id.signup_phone);

        signup_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String id = signup_id.getText().toString();
                String pw = signup_pw.getText().toString();
                String phone = signup_phone.getText().toString();
                dbHelper.addUser(id, pw, phone);
                Toast.makeText(SignupActivity.this, "가입성공", Toast.LENGTH_LONG).show();
            }
        });

    }
}
