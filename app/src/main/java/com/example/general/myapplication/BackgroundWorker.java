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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by General on 19-02-2017.
 */

    public class BackgroundWorker extends AsyncTask<String,Void,List<String>> {

public static List<String> list=new ArrayList<String>();
    Context context;
   private static final String TAG = MainActivity.class.getSimpleName();
    AlertDialog at;

    BackgroundWorker(Context ctx){context=ctx;}


       // @Override
        protected List<String> doInBackground(String... params) {
           String type=params[0];
           String login_url= "http://10.0.2.2/login.php";
           if(type.equals("login")){
                try {
                    String user_name=params[1];
                    String password=params[2];
                    URL url= new URL(login_url);
                    HttpURLConnection http=(HttpURLConnection)url.openConnection();
                    http.setRequestMethod("POST");
                    http.setDoOutput(true);
                    http.setDoInput(true);
                    OutputStream out= http.getOutputStream();
                    BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                    String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

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
                    list.add(result);
                    list.add(user_name);
                    return list ;

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


        protected void onPostExecute(List<String> result) {
            at.setMessage(result.get(0));
            at.show();
            if(result.get(0).equals("login succesful"))
            {

                Intent intent=new Intent(context,Search.class);
                intent.putExtra("user_id",result.get(1));
                context.startActivity(intent);
                ((Activity)context).finish();

            }
            else
            {
                result.clear();
                Intent intent=new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

