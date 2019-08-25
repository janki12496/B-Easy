package com.example.general.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {
    EditText Username1, Password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Username1=(EditText)findViewById(R.id.ed1);
        Password1=(EditText)findViewById(R.id.ed2);
    }

public void OnLogin(View view) {
    String username=Username1.getText().toString();
    String password=Password1.getText().toString();
    String type="login";
    BackgroundWorker bk=new BackgroundWorker(this);
            bk.execute(type,username,password);

}
}



