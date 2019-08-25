package com.example.general.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        String bookname = getIntent().getExtras().getString("Book_name");
        String userid = getIntent().getExtras().getString("user_id");
        final String TAG = book.class.getSimpleName();

        BookBack bk = new BookBack(this);
        bk.execute(bookname,userid);
    }

}
