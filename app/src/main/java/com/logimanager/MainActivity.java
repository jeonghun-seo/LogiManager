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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    TextInputEditText login_id;
    TextInputEditText login_pw;
    TextView sign_up;
    Button login_btn;
    private void copyDatabase() {

        String DB_PATH = "/data/data/" + getApplicationContext().getPackageName() + "/databases/";
        String DB_NAME = "product.db";

        try {
            // 디렉토리가 없으면, 디렉토리를 먼저 생성한다.
            File fDir = new File(DB_PATH);
            if (!fDir.exists()) {
                fDir.mkdir();
            }

            String strOutFile = DB_PATH + DB_NAME;
            InputStream inputStream = getApplicationContext().getAssets().open(DB_NAME);
            OutputStream outputStream = new FileOutputStream(strOutFile);

            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = inputStream.read(mBuffer)) > 0) {
                outputStream.write(mBuffer, 0, mLength);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabase();
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