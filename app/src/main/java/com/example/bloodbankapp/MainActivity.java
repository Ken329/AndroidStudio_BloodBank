package com.example.bloodbankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    LinearLayout search, add, detail, searchShow, addShow, detailShow;
    EditText name, email, blood, id, password;
    RadioGroup gender;
    RadioButton button;
    LinearLayout btnCreate;
    ImageButton down;
    AwesomeValidation validate;
    DatabaseReference ref;
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

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        blood = findViewById(R.id.editTextBlood);
        id = findViewById(R.id.editTextId);
        password = findViewById(R.id.editTextPassword);
        gender = findViewById(R.id.gender);
        btnCreate = findViewById(R.id.buttonCreate);
        down = findViewById(R.id.imageButtonDown);

        searchShow.setVisibility(View.INVISIBLE);
        detailShow.setVisibility(View.INVISIBLE);

        validate = new AwesomeValidation(ValidationStyle.BASIC);

        validate.addValidation(this, R.id.editTextName, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        validate.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        validate.addValidation(this, R.id.editTextId, ".{6,}", R.string.invalid_id);
        validate.addValidation(this, R.id.editTextPassword, ".{6,}", R.string.invalid_id);

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
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v);
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getButton();
                String myName = name.getText().toString();
                String myEmail = email.getText().toString();
                String myBlood = blood.getText().toString();
                String myId = id.getText().toString();
                String myPassword = password.getText().toString();
                String myGender = button.getText().toString();
                if(validate.validate()){
                    ref = FirebaseDatabase.getInstance().getReference("user");
                    detail d1 = new detail(myName, myId, myPassword, myGender, myEmail, myBlood);
                    ref.child(myId).setValue(d1);
                    Toast.makeText(MainActivity.this, "Successful Insert", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Check your detail input", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public void showPopUp(View v){
        PopupMenu menu = new PopupMenu(this, v);
        menu.setOnMenuItemClickListener(this);
        menu.inflate(R.menu.popup);
        menu.show();
    }

    public void goSearch(View v){
        Intent intent = new Intent(v.getContext(), searchPage.class);
        startActivity(intent);
    }
    public void goDetail(View v){
        Intent intent = new Intent(v.getContext(), detailPage.class);
        startActivity(intent);
    }
    public void getButton(){
        int btnId = gender.getCheckedRadioButtonId();
        button = findViewById(btnId);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                blood.setText("A");
                return true;
            case R.id.item2:
                blood.setText("A+");
                return true;
            case R.id.item3:
                blood.setText("B");
                return true;
            case R.id.item4:
                blood.setText("B+");
                return true;
            case R.id.item5:
                blood.setText("O");
                return true;
            default:
                return  false;
        }
    }
}