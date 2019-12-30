package com.example.werkstuk_arne_mergan.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;

import java.util.Date;
import java.util.List;
public class MainViewModel extends AndroidViewModel {
    private AsteroidRepo asteroidRepo;
    private List<Date> dates;

    public MainViewModel(@NonNull Application application, List<Date>dates) {
        super(application);
        asteroidRepo = new AsteroidRepo(application);
        this.dates = dates;
    }

    public LiveData<List<Asteroid>> GetAsteroidList(){
        return asteroidRepo.getAsteroids(dates);
    }
}
