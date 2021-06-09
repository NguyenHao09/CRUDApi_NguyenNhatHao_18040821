package com.example.crudapi_nguyennhathao_18040821;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {

    Button btnShowInfo, btnAdd, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        mappingId();

        btnShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManagerActivity.this, StudentInfoActivity.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManagerActivity.this, AddStudentActivity.class));
            }
        });



    }

    private void mappingId() {
        btnShowInfo = findViewById(R.id.btnShowInfo);
        btnAdd = findViewById(R.id.btnAddProduct);

    }
}