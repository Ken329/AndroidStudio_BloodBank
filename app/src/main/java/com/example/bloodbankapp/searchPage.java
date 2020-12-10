package com.example.bloodbankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class searchPage extends AppCompatActivity {
    LinearLayout search, add, detail, searchShow, addShow, detailShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        getSupportActionBar().hide();

        search = findViewById(R.id.linearSearch);
        add = findViewById(R.id.linearAdd);
        detail = findViewById(R.id.linearDetail);
        searchShow = findViewById(R.id.searchShow);
        addShow = findViewById(R.id.addShow);
        detailShow = findViewById(R.id.detailShow);

        detailShow.setVisibility(View.INVISIBLE);
        addShow.setVisibility(View.INVISIBLE);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAdd(v);
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDetail(v);
            }
        });
    }
    public void goAdd(View v){
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
    }
    public void goDetail(View v){
        Intent intent = new Intent(v.getContext(), detailPage.class);
        startActivity(intent);
    }
}