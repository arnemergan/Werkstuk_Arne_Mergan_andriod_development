package com.example.werkstuk_arne_mergan.repositories;

import android.content.Context;
import android.os.AsyncTask;

import com.example.werkstuk_arne_mergan.interfaces.AsteroidCallback;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidsCallback;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;
import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;
import com.example.werkstuk_arne_mergan.room.AsteroidDao;
import com.example.werkstuk_arne_mergan.room.AsteroidRoomDatabase;
import com.example.werkstuk_arne_mergan.services.AsteroidParser;
import com.example.werkstuk_arne_mergan.services.Helper;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class AsteroidRepo {
    public static class Task extends AsyncTask<List<Date>, Void, Asteroids> {
        private AsteroidsCallback asteroidsCallback;
        private Context context;
        private AsteroidDao asteroidDao;

        @Override
        protected Asteroids doInBackground(List<Date>... dates) {
            boolean connected = Helper.isConnected(context);
            Asteroids result = new Asteroids();
            List<Asteroid> asteroidList = null;
            List<String>datums = new Vector<>();
            for(Date date : dates[0]){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                datums.add(simpleDateFormat.format(date));
            }
            if (connected) {
                Asteroids asteroids;
                try {
                    asteroids = AsteroidParser.ParseAsteroids(DataSingleton.getInstance().downloadPlainText("https://api.nasa.gov/neo/rest/v1/feed?start_date="+ datums.get(0)+"&end_date="+datums.get(datums.size() - 1) +"&api_key=16Y4RrkQMXVR6yfSVeiaejNKkIq3pK2o7dgRrz1c"), datums);
                    insertAsteroids(asteroidDao,asteroids);
                    return asteroids;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                asteroidList = asteroidDao.GetAllAsteroids();
                if (!asteroidList.isEmpty()) {
                    for (Asteroid asteroid : asteroidList) {
                        asteroid.setCloseApproachData(asteroidDao.GetCloseApproachData(asteroid.getId()));
                    }
                }
                result.setNearEarthObjects(asteroidList);
            }
            return result;
        }

        public Task(AsteroidsCallback asteroidsCallback, Context context) {
            this.asteroidsCallback = asteroidsCallback;
            this.context = context;
            AsteroidRoomDatabase roomDatabase = AsteroidRoomDatabase.getDatabase(context);
            asteroidDao = roomDatabase.asteroidDao();
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

    public static class DetailTask extends AsyncTask<String, Void, Asteroid> {
        private AsteroidCallback asteroidsCallback;
        private Context context;
        private AsteroidDao asteroidDao;

        @Override
        protected Asteroid doInBackground(String... strings) {
            boolean connected = Helper.isConnected(context);
            Asteroid result = null;
            if (connected) {
                try {
                    Asteroid asteroid;
                    asteroid = AsteroidParser.ParseSingleAsteroid(DataSingleton.getInstance().downloadPlainText("https://api.nasa.gov/neo/rest/v1/neo/" + strings[0] + "?api_key=16Y4RrkQMXVR6yfSVeiaejNKkIq3pK2o7dgRrz1c"));
                    insertAsteroid(asteroidDao,asteroid);
                    return asteroid;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                result = asteroidDao.GetAsteroid(strings[0]);
                List<CloseApproachDatum> closeApproachData = asteroidDao.GetCloseApproachData(strings[0]);
                result.setCloseApproachData(closeApproachData);
            }
            return result;
        }

        public DetailTask(AsteroidCallback asteroidsCallback, Context context) {
            this.asteroidsCallback = asteroidsCallback;
            this.context = context;
            AsteroidRoomDatabase roomDatabase = AsteroidRoomDatabase.getDatabase(context);
            asteroidDao = roomDatabase.asteroidDao();
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

    public static void insertAsteroids(AsteroidDao asteroidDao,Asteroids... asteroids) {
        for (Asteroid asteroid : asteroids[0].getNearEarthObjects()) {
            List<CloseApproachDatum> closeApproachDatums = new Vector<>();
            for (CloseApproachDatum close : asteroid.getCloseApproachData()) {
                close.setAsteroid_id(asteroid.getId());
                closeApproachDatums.add(close);
            }
            if (asteroidDao.GetAsteroid(asteroid.getId()) == null) {
                asteroidDao.Insert(asteroid);
                asteroidDao.Insert(closeApproachDatums);
            } else {
                asteroidDao.Update(asteroid);
                asteroidDao.DeleteCloseApproachData(asteroid.getId());
                asteroidDao.Insert(closeApproachDatums);
            }
        }
    }

    public static void insertAsteroid(AsteroidDao asteroidDao,Asteroid asteroid) {
            List<CloseApproachDatum> closeApproachDatums = new Vector<>();
            for (CloseApproachDatum close : asteroid.getCloseApproachData()) {
                close.setAsteroid_id(asteroid.getId());
                closeApproachDatums.add(close);
            }
            if (asteroidDao.GetAsteroid(asteroid.getId()) == null) {
                asteroidDao.Insert(asteroid);
                asteroidDao.Insert(closeApproachDatums);
            } else {
                asteroidDao.Update(asteroid);
                asteroidDao.DeleteCloseApproachData(asteroid.getId());
                asteroidDao.Insert(closeApproachDatums);
            }
        }
}
