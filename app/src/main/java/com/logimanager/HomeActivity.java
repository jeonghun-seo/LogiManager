package com.logimanager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ItemDBHelper itemDBHelper = new ItemDBHelper(this);
    ListView db_table;
    Button btn_store;
    Button btn_release;
    private final ActivityResultLauncher<ScanOptions> store_scan_qr = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(HomeActivity.this, "취소", Toast.LENGTH_LONG).show();
                } else {
                    String data1 = result.getContents().toString();
                    Toast.makeText(HomeActivity.this,  data1+" 입고", Toast.LENGTH_LONG).show();
                    try {
                        itemDBHelper.increaseProductCount(data1);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    catch (Exception e){
                        Toast.makeText(HomeActivity.this,  e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
    private final ActivityResultLauncher<ScanOptions> release_scan_qr = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(HomeActivity.this, "취소", Toast.LENGTH_LONG).show();
                } else {
                    String data2 = result.getContents().toString();
                    Toast.makeText(HomeActivity.this,  data2+" 입고", Toast.LENGTH_LONG).show();
                    try {
                        itemDBHelper.decreaseProductCount(data2);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    catch (Exception e){
                        Toast.makeText(HomeActivity.this,  e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });

    public void getListViewOnDB(){
        List<String> itemList = new ArrayList<>();
        List<Integer> itemCountList = new ArrayList<>();
        List<Integer> imageList = new ArrayList<>();
        int num1 = itemDBHelper.getCountForItem("adidas");
        int num2 = itemDBHelper.getCountForItem("nike");
        int num3 = itemDBHelper.getCountForItem("vans");
        int num4 = itemDBHelper.getCountForItem("converse");
        int num5 = itemDBHelper.getCountForItem("asics");
        itemList.add("adidas");
        itemList.add("nike");
        itemList.add("vans");
        itemList.add("converse");
        itemList.add("asics");
        itemCountList.add(num1);
        itemCountList.add(num2);
        itemCountList.add(num3);
        itemCountList.add(num4);
        itemCountList.add(num5);
        imageList.add(R.drawable.adidas);
        imageList.add(R.drawable.nike);
        imageList.add(R.drawable.vans);
        imageList.add(R.drawable.converse);
        imageList.add(R.drawable.asics);

        CustomAdapter adapter = new CustomAdapter(this, itemList, itemCountList, imageList);
        db_table.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_store = findViewById(R.id.btn_store);
        btn_release = findViewById(R.id.btn_release);
        db_table = findViewById(R.id.db_table);

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
        getListViewOnDB();
    }
}