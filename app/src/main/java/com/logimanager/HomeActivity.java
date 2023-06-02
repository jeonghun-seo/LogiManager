package com.logimanager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class HomeActivity extends AppCompatActivity {
    Button btn_store;
    Button btn_release;
    private final ActivityResultLauncher<ScanOptions> store_scan_qr = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(HomeActivity.this, "취소", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HomeActivity.this,  result.getContents()+" 입고", Toast.LENGTH_LONG).show();
                }
            });
    private final ActivityResultLauncher<ScanOptions> release_scan_qr = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(HomeActivity.this, "취소", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HomeActivity.this,  result.getContents()+" 출고", Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_store = findViewById(R.id.btn_store);
        btn_release = findViewById(R.id.btn_release);

        btn_store.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                store_scan_qr.launch(new ScanOptions());
            }
        });
        btn_release.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                release_scan_qr.launch(new ScanOptions());
            }
        });

    }
}