package com.example.werkstuk_arne_mergan.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.werkstuk_arne_mergan.interfaces.AsteroidCallback;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;

public class DetailViewModel {
    private LiveData<Asteroid> asteroid;
    private AsteroidRepo asteroidRepo;

    public DetailViewModel(Context context) {
        asteroidRepo = new AsteroidRepo(context);
    }

    public LiveData<Asteroid> GetAsteroid(String id){
        asteroid = asteroidRepo.getAsteroid(id);
        return asteroid;
    }
}
