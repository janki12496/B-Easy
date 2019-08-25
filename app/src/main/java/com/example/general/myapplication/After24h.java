package com.example.general.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class After24h extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getExtras().getString("book_id");
        final String TAG = details.class.getSimpleName();
        Log.d(TAG, title);
        AsyncAfter bk=new AsyncAfter(this);
        bk.execute(title);
    }

    private class AsyncAfter extends AsyncTask<String,Void,String> {
        Context context;
        final String TAG = AsyncAfter.class.getSimpleName();
        AlertDialog at;

        AsyncAfter(Context ctx) {
            context = ctx;
        }


        // @Override
        protected String doInBackground(String... params) {

            String login_url = "http://10.0.2.2/after24.php";
            try {
                String book_name = params[0];
                URL url = new URL(login_url);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setDoInput(true);
                OutputStream out = http.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                String post_data = URLEncoder.encode("book_id", "UTF-8") + "=" + URLEncoder.encode(book_name, "UTF-8");

                bw.write(post_data);
                bw.flush();
                bw.close();
                out.close();

                InputStream in = http.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
                String line = "";
                String result = "";
                while ((line = br.readLine()) != null) {
                    result += line;
                }
                br.close();
                in.close();
                http.disconnect();
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            at = new AlertDialog.Builder(context).create();
            at.setTitle("Status");
        }

        protected void onPostExecute(String result) {
            if (result.equals("Cancled")) {
                Intent i=new Intent(context,add.class);
                i.putExtra("abc",result);
                context.startActivity(i);
                finish();
            }
            else
            {
                Intent i=new Intent(context,add.class);
                i.putExtra("abc",result);
                context.startActivity(i);
                finish();
            }

        }
    }
}
