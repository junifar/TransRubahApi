package com.trans.junifar.transp.async;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.trans.junifar.transpot.ListOperatorActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class RetrieveBrands extends AsyncTask<Object, Object, String> {

    private ProgressBar progressBar;
    private ListView listView;
    private ListOperatorActivity listOperatorActivity;
    private String urlAddress = "http://192.168.20.251:8000/mission/?format=json";

    public RetrieveBrands(ProgressBar progressBar, ListView listView, ListOperatorActivity listOperatorActivity) {
        this.progressBar = progressBar;
        this.listView = listView;
        this.listOperatorActivity = listOperatorActivity;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Object[] params) {
        try {
            URL url = new URL(urlAddress);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            try {
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
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(String response) {
        if (response == null) {
            response = "Error";
        }


        ArrayAdapter<String> arrayAdapter;
        if (getReponseItem(response) != null) {
            arrayAdapter = new ArrayAdapter<>(listOperatorActivity, android.R.layout.simple_list_item_1, android.R.id.text1, getReponseItem(response));
        } else {
            String[] values = new String[]{
                    "No Data Found",
            };
            arrayAdapter = new ArrayAdapter<>(listOperatorActivity, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        }
        listView.setAdapter(arrayAdapter);


        progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
    }

    private ArrayList<String> getReponseItem(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            ArrayList<String> items = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json_data = jsonArray.getJSONObject(i);
                String name = json_data.getString("name");
                items.add(name);
            }

            return items;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
