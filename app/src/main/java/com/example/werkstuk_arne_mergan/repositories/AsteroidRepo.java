package com.example.werkstuk_arne_mergan.repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.werkstuk_arne_mergan.interfaces.AsteroidCallback;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidsCallback;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;
import com.example.werkstuk_arne_mergan.services.AsteroidParser;
import com.example.werkstuk_arne_mergan.services.Helper;

import org.json.JSONException;

import java.util.Vector;

public class AsteroidRepo {
    public static class Task extends AsyncTask<Void,Void, Asteroids> {
        private AsteroidsCallback asteroidsCallback;
        private Context context;
        @Override
        protected Asteroids doInBackground(Void... voids) {
            boolean connected = Helper.isConnected(context);
            Asteroids result = new Asteroids();
            Vector<String> dates = new Vector<>();
            dates.add("2015-09-08");
            if(connected){
                try {
                    result = AsteroidParser.ParseAsteroids(DataSingleton.getInstance().downloadPlainText("https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=16Y4RrkQMXVR6yfSVeiaejNKkIq3pK2o7dgRrz1c"),dates);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{

            }
            return result;
        }

        public Task(AsteroidsCallback asteroidsCallback,Context context) {
            this.asteroidsCallback = asteroidsCallback;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Asteroids result) {
            super.onPostExecute(result);
            try {
                asteroidsCallback.onTaskCompleted(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DetailTask extends AsyncTask<String,Void,Asteroid> {
        private AsteroidCallback asteroidsCallback;
        private Context context;

        @Override
        protected Asteroid doInBackground(String... strings) {
            boolean connected = Helper.isConnected(context);
            Asteroid result = new Asteroid();
            if(connected){
                try {
                    result = AsteroidParser.ParseSingleAsteroid(DataSingleton.getInstance().downloadPlainText("https://api.nasa.gov/neo/rest/v1/neo/" + strings[0] + "?api_key=16Y4RrkQMXVR6yfSVeiaejNKkIq3pK2o7dgRrz1c"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{

            }
            return result;
        }

        public DetailTask(AsteroidCallback asteroidsCallback, Context context) {
            this.asteroidsCallback = asteroidsCallback;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Asteroid result) {
            super.onPostExecute(result);
            try {
                asteroidsCallback.onTaskCompleted(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
