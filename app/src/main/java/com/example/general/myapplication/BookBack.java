package com.example.general.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import java.util.Timer;
import java.util.TimerTask;


public class BookBack extends AsyncTask<String,Void,String> {
   Context context;
    private static final String TAG = BookBack.class.getSimpleName();
    AlertDialog at;

    BookBack(Context ctx){context=ctx;}


    // @Override
    protected String doInBackground(String... params) {

        String login_url= "http://10.0.2.2/book.php";
        try {
            String user_name=params[1];
            String book_name=params[0];
            URL url= new URL(login_url);
            HttpURLConnection http=(HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setDoInput(true);
            OutputStream out= http.getOutputStream();
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                    +URLEncoder.encode("bkname","UTF-8")+"="+URLEncoder.encode(book_name,"UTF-8");

            bw.write(post_data);
            bw.flush();
            bw.close();
            out.close();

            InputStream in=http.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(in,"iso-8859-1"));
            String line="";
           String result="";
            while((line=br.readLine())!=null)
            {
                result +=line;
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
        at=new AlertDialog.Builder(context).create();
        at.setTitle("Book Status");
    }


    protected void onPostExecute(String res) {
        // at.setMessage(result.get(0));
        final String a=res;
        if (res.equals("Not Allowed")) {
            at.setMessage("NOT ALLOWED");
            at.show();
            Intent i =new Intent(context,Search.class);
            context.startActivity(i);
            ((Activity)context).finish();
           }
        else {
            at.setMessage("Book Succesfully\n Id:" + res);
            at.show();

            Timer timer = new Timer();
            TimerTask t = new TimerTask() {
                @Override
                public void run() {
                    Intent nextPag = new Intent(context, After24h.class);
                    nextPag.putExtra("book_id",a);
                    context.startActivity(nextPag);
                    ((Activity)context).finish();
                }
            };

            timer.schedule(t, 1000*10);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}



