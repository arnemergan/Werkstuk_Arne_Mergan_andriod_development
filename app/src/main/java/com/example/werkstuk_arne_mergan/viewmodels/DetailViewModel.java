package com.example.werkstuk_arne_mergan.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.werkstuk_arne_mergan.interfaces.AsteroidCallback;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;

public class DetailViewModel {
    private MutableLiveData<Asteroid> asteroid = new MutableLiveData<>();
    private Context context;

    public DetailViewModel(Context context) {
        this.context = context;
    }

    public LiveData<Asteroid> GetAsteroid(String id){
        LoadAsteroid(id);
        return asteroid;
    }

    private void LoadAsteroid(String id) {
        AsteroidRepo.DetailTask detailTask = new AsteroidRepo.DetailTask(new AsteroidCallback() {
            @Override
            public void onTaskCompleted(Asteroid result) {
                asteroid.setValue(result);
            }
        }, context);
        detailTask.execute(id);
    }
}
