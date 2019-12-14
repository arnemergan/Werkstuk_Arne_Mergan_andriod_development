package com.example.werkstuk_arne_mergan.remote;
import com.example.werkstuk_arne_mergan.models.Asteroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AsteroidService {
    @GET("/3542519?api_key=DEMO_KEY")
    Call<Asteroid> GetAsteroid();
}
