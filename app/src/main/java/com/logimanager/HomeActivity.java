package com.logimanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btn_store,btn_release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void InitializeView()
    {

    }
}