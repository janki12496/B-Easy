package com.example.general.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUp extends AppCompatActivity {
    EditText Username1, Year1, Branch1, id1, Password1;
RadioGroup ra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Username1 = (EditText) findViewById(R.id.ed1);
        Year1 = (EditText) findViewById(R.id.ed2);
        Branch1 = (EditText) findViewById(R.id.ed3);
        id1 = (EditText) findViewById(R.id.ed4);
        Password1 = (EditText) findViewById(R.id.ed5);
    }

    public void onsubmit(View view) {
        String username = Username1.getText().toString();
        String password = Password1.getText().toString();
        String year = Year1.getText().toString();
        String branch = Branch1.getText().toString();
        String Id = id1.getText().toString();
        String type = "login";
        int limit = 0;

        BackgroundSignUp bk = new BackgroundSignUp(this);
        bk.execute(type, username, password, year, branch, Id, String.valueOf(limit));

    }
}


