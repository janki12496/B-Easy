package com.example.general.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by General on 19-02-2017.
 */

public class BackgroundSignUp extends AsyncTask<String,Void,String> {

    Context context;
    private static final String TAG = MainActivity.class.getSimpleName();
    AlertDialog at;
    BackgroundSignUp(Context ctx){context=ctx;}

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url= "http://10.0.2.2/signup.php";
        if(type.equals("login")){
            try {
                String user_name=params[1];
                String password=params[2];
                String year=params[3];
                String branch=params[4];
                String Id=params[5];
                String limit=params[6];
                URL url= new URL(login_url);
                HttpURLConnection http=(HttpURLConnection)url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setDoInput(true);
                OutputStream out= http.getOutputStream();
                BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String post_data= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("year","UTF-8")+"="+URLEncoder.encode(year,"UTF-8")+"&"
                        +URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"
                        +URLEncoder.encode("Id","UTF-8")+"="+URLEncoder.encode(Id,"UTF-8")+"&"
                        +URLEncoder.encode("limit","UTF-8")+"="+URLEncoder.encode(limit,"UTF-8");

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

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        at=new AlertDialog.Builder(context).create();
        at.setTitle("hello");
    }


    protected void onPostExecute(String result) {
        at.setMessage(result);
        at.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

