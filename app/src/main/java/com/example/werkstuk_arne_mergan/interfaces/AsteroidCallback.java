package com.example.werkstuk_arne_mergan.interfaces;

import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;

import org.json.JSONException;

public interface AsteroidCallback {
    public void onTaskCompleted(Asteroid result) throws JSONException;
}
