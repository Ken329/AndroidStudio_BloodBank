package com.example.bloodbankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class searchPage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    LinearLayout search, add, detail, searchShow, addShow, detailShow;
    TextView blood;
    ImageButton dropdown;
    ListView list;
    ArrayList<String> array;
    ArrayAdapter adapter;
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
        blood = findViewById(R.id.textViewBloodtype);
        dropdown = findViewById(R.id.imageButtonDropdown);
        list = findViewById(R.id.listView);

        detailShow.setVisibility(View.INVISIBLE);
        addShow.setVisibility(View.INVISIBLE);

        array = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        list.setAdapter(adapter);

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
        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v);
            }
        });
    }
    public void getBlood(String blood){
        Query query = FirebaseDatabase.getInstance().getReference("user")
                .orderByChild("blood")
                .equalTo(blood);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                array.clear();
                for(DataSnapshot s1 : snapshot.getChildren()){
                    array.add(s1.child("name").getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void showPopUp(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup);
        popupMenu.show();
    }
    public void goAdd(View v){
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
    }
    public void goDetail(View v){
        Intent intent = new Intent(v.getContext(), detailPage.class);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                blood.setText("A");
                getBlood("A");
                return  true;
            case R.id.item2:
                blood.setText("A+");
                getBlood("A+");
                return true;
            case R.id.item3:
                blood.setText("B");
                getBlood("B");
                return true;
            case R.id.item4:
                blood.setText("B+");
                getBlood("B+");
                return true;
            case R.id.item5:
                blood.setText("O");
                getBlood("O");
                return true;
            default:
                return false;
        }
    }
}