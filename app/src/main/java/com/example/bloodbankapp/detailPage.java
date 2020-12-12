package com.example.bloodbankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class detailPage extends AppCompatActivity implements dialog.ExampleDialogListener {
    LinearLayout search, add, detail, searchShow, addShow, detailShow;
    TextView name, pass, blood, email, gender;
    DatabaseReference ref;
    String loginUser;
    Boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        getSupportActionBar().hide();

        search = findViewById(R.id.linearSearch);
        add = findViewById(R.id.linearAdd);
        detail = findViewById(R.id.linearDetail);
        searchShow = findViewById(R.id.searchShow);
        addShow = findViewById(R.id.addShow);
        detailShow = findViewById(R.id.detailShow);

        name = findViewById(R.id.textViewName);
        pass = findViewById(R.id.textViewPass);
        blood = findViewById(R.id.textViewBlood);
        email = findViewById(R.id.textViewEmail);
        gender = findViewById(R.id.textViewGender);

        searchShow.setVisibility(View.INVISIBLE);
        addShow.setVisibility(View.INVISIBLE);

        openDialog();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAdd(v);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch(v);
            }
        });
    }
    public void openDialog(){
        dialog dialog = new dialog();
        dialog.show(getSupportFragmentManager(), "Example Dialog");
    }

    public void goSearch(View v){
        Intent intent = new Intent(v.getContext(), searchPage.class);
        startActivity(intent);
    }
    public void goAdd(View v){
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
    }
    public void goBack(){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void applyTexts(String username, String password) {
        Query query = FirebaseDatabase.getInstance().getReference("user")
                .orderByChild("id")
                .equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String myPass = snapshot.child(username).child("password").getValue().toString();
                    if(password.equals(myPass)){
                        String myBlood = snapshot.child(username).child("blood").getValue().toString();
                        String myEmail = snapshot.child(username).child("email").getValue().toString();
                        String myGender = snapshot.child(username).child("gender").getValue().toString();

                        name.setText(username);
                        pass.setText(password);
                        blood.setText(myBlood);
                        email.setText(myEmail);
                        gender.setText(myGender);
                        Toast.makeText(getApplicationContext(), "Successful Login", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
                        goBack();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "No username found", Toast.LENGTH_LONG).show();
                    goBack();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}