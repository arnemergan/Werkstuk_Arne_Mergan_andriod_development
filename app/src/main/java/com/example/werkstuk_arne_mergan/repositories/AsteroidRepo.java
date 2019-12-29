package com.example.werkstuk_arne_mergan.repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;
import com.example.werkstuk_arne_mergan.room.AsteroidDao;
import com.example.werkstuk_arne_mergan.room.AsteroidRoomDatabase;
import com.example.werkstuk_arne_mergan.services.AsteroidParser;
import com.example.werkstuk_arne_mergan.services.Helper;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidCallback;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidsCallback;
import org.json.JSONException;
import java.util.Date;
import java.util.List;

public class AsteroidRepo {

    private AsteroidDao asteroidDao;
    private Context context;
    private MutableLiveData<Asteroid> asteroidMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Asteroid>> asteroidsMutableLiveData = new MutableLiveData<>();

    public AsteroidRepo(Context context) {
        this.context = context;
        AsteroidRoomDatabase roomDatabase = AsteroidRoomDatabase.getDatabase(context);
        asteroidDao = roomDatabase.asteroidDao();
    }

    public Boolean getConnection(){
        return Helper.isConnected(context);
    }

    public LiveData<Asteroid> getAsteroid(String id){
        if(getConnection()){
            new GetTask(new AsteroidCallback() {
                @Override
                public void onTaskCompleted(Asteroid result) throws JSONException {
                    if(result != null){
                        new InsertTask(asteroidDao).execute(result);
                        asteroidMutableLiveData.postValue(result);
                    }
                }
            }).execute(id);
            return asteroidMutableLiveData;
        }else{
            return asteroidDao.GetAsteroid(id);
        }
    }

    public LiveData<List<Asteroid>> getAsteroids(List<Date>dates) {
        List<String> datums = AsteroidParser.ParseDates(dates);
        if(getConnection()) {
            //noinspection unchecked
            new GetListTask(new AsteroidsCallback() {
                @SuppressWarnings("unchecked")
                @Override
                public void onTaskCompleted(Asteroids result) {
                    if (result != null) {
                    if (result.getNearEarthObjects() != null) {
                        for (Asteroid asteroid : result.getNearEarthObjects()) {
                            new InsertTask(asteroidDao).execute(asteroid);
                        }
                        asteroidsMutableLiveData.postValue(result.getNearEarthObjects());
                    }
                }
            }
            }).execute(datums);
            return asteroidsMutableLiveData;
        }else{
            return asteroidDao.GetAllAsteroids();
        }
    }

    public static class GetListTask extends AsyncTask<List<String>,Void,Asteroids>{
        private AsteroidsCallback asteriodsCallback;

        public GetListTask(AsteroidsCallback asteriodsCallback) {
            this.asteriodsCallback = asteriodsCallback;
        }

        @Override
        protected Asteroids doInBackground(List<String>... datums) {
            try {
                Asteroids asteroids = AsteroidParser.ParseAsteroids(DataSingleton.getInstance().downloadPlainText("https://api.nasa.gov/neo/rest/v1/feed?start_date="+ datums[0].get(0)+"&end_date="+datums[0].get(datums[0].size() - 1) +"&api_key=16Y4RrkQMXVR6yfSVeiaejNKkIq3pK2o7dgRrz1c"), datums[0]);
                return asteroids;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Asteroids asteroids) {
            super.onPostExecute(asteroids);
            try {
                asteriodsCallback.onTaskCompleted(asteroids);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class GetTask extends AsyncTask<String,Void,Asteroid>{
        private AsteroidCallback asteroidCallback;

        public GetTask(AsteroidCallback asteroidCallback) {
            this.asteroidCallback = asteroidCallback;
        }

        @Override
        protected Asteroid doInBackground(String... strings) {
            try {
                return AsteroidParser.ParseSingleAsteroid(DataSingleton.getInstance().downloadPlainText("https://api.nasa.gov/neo/rest/v1/neo/" + strings[0] + "?api_key=16Y4RrkQMXVR6yfSVeiaejNKkIq3pK2o7dgRrz1c"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Asteroid asteroid) {
            super.onPostExecute(asteroid);
            try {
                asteroidCallback.onTaskCompleted(asteroid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static class InsertTask extends AsyncTask<Asteroid, Void, Void> {
        private AsteroidDao asteroidDao;

        public InsertTask(AsteroidDao asteroidDao) {
            this.asteroidDao = asteroidDao;
        }

        @Override
        protected Void doInBackground(Asteroid... asteroids) {
            asteroidDao.Insert(asteroids[0]);
            asteroidDao.DeleteCloseApproachData(asteroids[0].getId());
            asteroidDao.Insert(asteroids[0].getCloseApproachData());
            return null;
        }
    }

    public static class UpdateTask extends AsyncTask<Asteroid, Void, Void> {
        private AsteroidDao asteroidDao;

        public UpdateTask(AsteroidDao asteroidDao) {
            this.asteroidDao = asteroidDao;
        }

        @Override
        protected Void doInBackground(Asteroid... asteroids) {
            asteroidDao.Update(asteroids[0]);
            asteroidDao.DeleteCloseApproachData(asteroids[0].getId());
            asteroidDao.Insert(asteroids[0].getCloseApproachData());
            return null;
        }
    }

    public static class DeleteTask extends AsyncTask<Asteroid, Void, Void> {
        private AsteroidDao asteroidDao;

        public DeleteTask(AsteroidDao asteroidDao) {
            this.asteroidDao = asteroidDao;
        }

        @Override
        protected Void doInBackground(Asteroid... asteroids) {
            asteroidDao.Delete(asteroids[0]);
            return null;
        }
    }
}
