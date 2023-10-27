package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class mainpage_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.main_btn_record) {
            Intent it1 = new Intent(this, RecordActivity.class);  //跳转界面
            startActivity(it1);
        }
    }
}
