package com.riyan.viewhtml;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by riyan on 16/10/2017.
 */

public class ConnectInternet extends AsyncTask<String,Void,String> {

    Context context;
    public ConnectInternet(Context ct){
        context = ct;
    }
    @Override
    protected String doInBackground(String... strings) {
        String s1= strings[0];
        InputStream in;
        try {
            URL myUrl = new URL(s1);
            HttpURLConnection myCon = (HttpURLConnection) myUrl.openConnection();
            myCon.setReadTimeout(10000);
            myCon.setConnectTimeout(20000);
            myCon.setRequestMethod("GET");
            myCon.connect();

            in = myCon.getInputStream();

            BufferedReader myBuf = new BufferedReader(new InputStreamReader(in));
            StringBuilder st = new StringBuilder();
            String line= "";

            while ((line = myBuf.readLine())!=null){
                st.append(line+"   \n");
            }
            myBuf.close();
            in.close();

            return st.toString();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Pesan ","No koneksi");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.mytext.setText(s);
    }
}
