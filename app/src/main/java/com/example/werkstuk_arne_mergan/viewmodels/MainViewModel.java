package com.example.werkstuk_arne_mergan.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.werkstuk_arne_mergan.interfaces.AsteroidsCallback;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;

import java.util.List;

public class MainViewModel {
    private MutableLiveData<List<Asteroid>>  asteroids = new MutableLiveData<>();
    private Context context;

    public MainViewModel(Context context) {
        this.context = context;
    }

    public LiveData<List<Asteroid>> GetAsteroidList(){
        LoadAsteroids();
        return asteroids;
    }

    private void LoadAsteroids() {
        AsteroidRepo.Task task = new AsteroidRepo.Task(new AsteroidsCallback() {
            @Override
            public void onTaskCompleted(Asteroids result) {
                asteroids.setValue(result.getNearEarthObjects());
            }
        }, context);
        task.execute();
    }
}
