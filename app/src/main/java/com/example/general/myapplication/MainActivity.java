package com.example.general.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signup(View view) {
        Intent nextPage = new Intent(MainActivity.this, SignUp.class);
        startActivity(nextPage);
        finish();
    }

    public void signin(View view) {
        Intent nextPag = new Intent(MainActivity.this, SignIn.class);
        startActivity(nextPag);
        finish();
    }
}