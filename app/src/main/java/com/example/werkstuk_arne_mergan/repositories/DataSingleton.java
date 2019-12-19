package com.example.werkstuk_arne_mergan.repositories;

import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataSingleton {
    private DataSingleton() { }

    private ArrayList<Asteroid> mItems;
    private String mDataString;
    // singleton code omitted in this example
    public ArrayList<Asteroid> getItems() {
        if (mItems == null) {
            mItems = new ArrayList<>();
        }
        return mItems;
    }
    public String getDataString() {
        return mDataString;
    }
    public void setDataString(String s) {
        mDataString = s;
    }
    private static class SingletonHelper {
        private static final DataSingleton INSTANCE = new DataSingleton();
    }
    public static DataSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
    protected String downloadPlainText(String uri) {
        StringBuilder content = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return content.toString(); }
}
