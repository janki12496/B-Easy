package com.example.general.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final String TAG = details.class.getSimpleName();
        String title = getIntent().getExtras().getString("Title");
       // Log.d(TAG, title);
        new AsyncFetch().execute(title);
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(details.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading....");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides or your JSON file address
                url = new URL("http://10.0.2.2/detail.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

                // setDoOutput to true as we receive data
                conn.setDoOutput(true);
                conn.setDoInput(true);


                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                final String TAG = details.class.getSimpleName();
                String title = params[0];
                Log.d(TAG, "hello" + title);
                OutputStream out = conn.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                String post_data = URLEncoder.encode("bl", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");

                bw.write(post_data);
                bw.flush();
                bw.close();
                out.close();
                Log.d(TAG, "hello0");
                // Read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line = "";
                Log.d(TAG, "hello1");
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    Log.d(TAG, "hello2");
                }

                // Pass data to onPostExecute method
                return (result.toString());

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            ArrayList<String> dataList = new ArrayList<String>();
            pdLoading.dismiss();


            if (result.equals("no rows")) {

                // Do some action if no data from database

            } else {
                final String TAG = details.class.getSimpleName();
                try {

                    JSONArray jArray = new JSONArray(result);
                    final String TAG1 = details.class.getSimpleName();
                    // Extract data from json and store into ArrayList
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        String bkname = json_data.getString("book_name");

                        TextView textView = (TextView) findViewById(R.id.t1);
                        textView.setText(bkname);
                        String pub = json_data.getString("publication");
                        TextView textView1 = (TextView) findViewById(R.id.t2);
                        textView1.setText(pub);
                        String aut = json_data.getString("author");
                        TextView textView2 = (TextView) findViewById(R.id.t3);
                        textView2.setText(aut);
                        String ed = json_data.getString("edition");
                        TextView textView3 = (TextView) findViewById(R.id.t4);
                        textView3.setText(ed);
                        String pg = json_data.getString("nop");
                        TextView textView4 = (TextView) findViewById(R.id.t5);
                        textView4.setText(pg);
                        String tb = json_data.getString("total_books");
                        TextView textView5 = (TextView) findViewById(R.id.t6);
                        textView5.setText(tb);
                        String bo = json_data.getString("books_online");
                        TextView textView6 = (TextView) findViewById(R.id.t7);
                        textView6.setText(bo);

                    }

                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(details.this, e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(details.this, result.toString(), Toast.LENGTH_LONG).show();
                }

            }


        }
    }

    public void book1(View view) {

            Intent intent = new Intent(details.this, book.class);
            String t = getIntent().getExtras().getString("Title");
            String t1 = getIntent().getExtras().getString("Title1");
            intent.putExtra("Book_name", t);
            intent.putExtra("user_id", t1);
            final String TAG = details.class.getSimpleName();
            startActivity(intent);
            finish();
        }

}