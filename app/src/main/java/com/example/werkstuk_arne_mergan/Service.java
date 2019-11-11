package com.example.werkstuk_arne_mergan;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

//bron: https://developer.android.com/training/volley/simple

public class Service {

    public String[] GetResponse(){
        final String[] res = {""};
        // Instantiate the RequestQueue.

        return res;
    }

    public Asteroid GetAsteriod(int id){
        Asteroid asteroid = new Asteroid();
        return asteroid;
    }

    public List<Asteroid> GetAsteroids(Date begin, Date close){
        List<Asteroid>asteroids = new Vector<Asteroid>();
        return asteroids;
    }
}
