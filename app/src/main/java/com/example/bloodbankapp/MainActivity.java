package com.example.bloodbankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout search, add, detail, searchShow, addShow, detailShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        search = findViewById(R.id.linearSearch);
        add = findViewById(R.id.linearAdd);
        detail = findViewById(R.id.linearDetail);
        searchShow = findViewById(R.id.searchShow);
        addShow = findViewById(R.id.addShow);
        detailShow = findViewById(R.id.detailShow);

        searchShow.setVisibility(View.INVISIBLE);
        detailShow.setVisibility(View.INVISIBLE);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch(v);
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDetail(v);
            }
        });
    }
    public void goSearch(View v){
        Intent intent = new Intent(v.getContext(), searchPage.class);
        startActivity(intent);
    }
    public void goDetail(View v){
        Intent intent = new Intent(v.getContext(), detailPage.class);
        startActivity(intent);
    }
}