package com.trans.junifar.transp.async;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by prasetia on 6/14/2016.
 */
public class RetrieveBrands extends AsyncTask<Object, Object, String> {

    private ProgressBar progressBar;
    private TextView textView;
    private String urlAddress = "http://192.168.20.251:8000/member/?format=json";

    public RetrieveBrands(ProgressBar progressBar, TextView textView) {
        this.textView = textView;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Object[] params) {
        //progressBar = (ProgressBar) params[0];
        //textView = (TextView) params[1];

        try {
            URL url = new URL(urlAddress);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            urlConnection.connect();

            try {
                int status = urlConnection.getResponseCode();
                Log.i("WARNING : ", urlConnection.getResponseMessage());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } finally {
                urlConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            Log.e("ERROR", e.getMessage(), e);
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String response) {
        if (response == null) {
            response = "Error2";
        }
        progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
        textView.setText(response);
    }
}
