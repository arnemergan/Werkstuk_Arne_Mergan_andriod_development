package com.example.werkstuk_arne_mergan.viewmodels;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;

import java.util.Date;
import java.util.List;
public class MainViewModel {
    private LiveData<List<Asteroid>> asteroids;
    private AsteroidRepo asteroidRepo;

    public MainViewModel(Context context,List<Date>dates) {
        asteroidRepo = new AsteroidRepo(context);
        asteroids = asteroidRepo.getAsteroids(dates);
    }

    public LiveData<List<Asteroid>> GetAsteroidList(List<Date>dates){
        asteroids = asteroidRepo.getAsteroids(dates);
        return asteroids;
    }
}
