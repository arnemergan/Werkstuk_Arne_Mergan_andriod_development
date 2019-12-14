package com.example.werkstuk_arne_mergan.remote;

public class AsteroidsUtils {
    public static final String BASE_URL = "https://api.nasa.gov/neo/rest/v1/";

    public static AsteroidService GetAsteroidService() {
        return RetrofitClient.getClient(BASE_URL).create(AsteroidService.class);
    }
}
