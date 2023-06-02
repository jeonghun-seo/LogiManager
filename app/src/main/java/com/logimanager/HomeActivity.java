package com.logimanager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class HomeActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private SimpleCursorAdapter adapter;
    ListView db_table;
    ItemDBHelper itemDBHelper = new ItemDBHelper(this);
    Button btn_store;
    Button btn_release;
    private final ActivityResultLauncher<ScanOptions> store_scan_qr = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(HomeActivity.this, "취소", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HomeActivity.this,  result.getContents()+" 입고", Toast.LENGTH_LONG).show();
                    itemDBHelper.insertProduct(result.getContents(),(1));
                }
            });
    private final ActivityResultLauncher<ScanOptions> release_scan_qr = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(HomeActivity.this, "취소", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(HomeActivity.this,  result.getContents()+" 출고", Toast.LENGTH_LONG).show();
                    itemDBHelper.decreaseProductCount(result.getContents());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_store = findViewById(R.id.btn_store);
        btn_release = findViewById(R.id.btn_release);
        db_table = findViewById(R.id.db_table);

        db = itemDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, count FROM products", null);
        String[] fromColumns = { "id", "count" };

        // View ID 배열
        int[] toViews = { android.R.id.text1, android.R.id.text2 };

        // 어댑터 생성
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, fromColumns, toViews, 0);

        // ListView에 어댑터 설정
        db_table.setAdapter(adapter);

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
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 사용한 리소스 정리
        if (adapter != null) {
            adapter.swapCursor(null);
        }
        if (db != null) {
            db.close();
        }
        if (itemDBHelper != null) {
            itemDBHelper.close();
        }
    }
}