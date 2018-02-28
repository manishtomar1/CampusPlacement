package com.example.ashish.campusplacement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainLogin extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Using database from DatabaseHelper class
        db = DatabaseHelper.getInstance(this);

        super.onCreate(savedInstanceState);
    }
}
