package com.example.werkstuk_arne_mergan.interfaces;

import com.example.werkstuk_arne_mergan.models.Asteroids;

import org.json.JSONException;

public interface AsteroidsCallback {
    public void onTaskCompleted(Asteroids result) throws JSONException;
}
