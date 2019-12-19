package com.example.werkstuk_arne_mergan.services;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;
import android.util.Log;

import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;
import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;
import com.example.werkstuk_arne_mergan.models.EstimatedDiameter;
import com.example.werkstuk_arne_mergan.models.Feet;
import com.example.werkstuk_arne_mergan.models.Kilometers;
import com.example.werkstuk_arne_mergan.models.Links;
import com.example.werkstuk_arne_mergan.models.Miles;
import com.example.werkstuk_arne_mergan.models.MissDistance;
import com.example.werkstuk_arne_mergan.models.OrbitClass;
import com.example.werkstuk_arne_mergan.models.RelativeVelocity;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public abstract class AsteroidParser {
    public static Asteroids ParseAsteroids(String result, List<String> dates) throws JSONException {
        Asteroids asteroids = new Asteroids();
        Links links = new Links();
        List<Asteroid> NearEarthObjects = new Vector<Asteroid>();
        JSONObject jObj = new JSONObject(result);
        JSONObject links_json =  jObj.getJSONObject("links");
        links.setNext(links_json.getString("next"));
        links.setPrev(links_json.getString("prev"));
        links.setSelf(links_json.getString("self"));
        JSONObject near_earth_objects = jObj.getJSONObject("near_earth_objects");
        for (String date: dates) {
            JSONArray date_array  = near_earth_objects.getJSONArray(date);
            for(int i=0; i < date_array.length(); i++){
                JSONObject obj = date_array.getJSONObject(i);
                NearEarthObjects.add(ParseAsteroid(obj));
            }
        }
        asteroids.setElementCount(Integer.parseInt(jObj.getString("element_count")));
        asteroids.setLinks(links);
        asteroids.setNearEarthObjects(NearEarthObjects);
        return  asteroids;
    }

    public static Asteroid ParseSingleAsteroid(String result) throws JSONException{
        Asteroid asteroid;
        JSONObject obj = new JSONObject(result);
        asteroid = ParseAsteroid(obj);
        /*OrbitClass orbital = new OrbitClass();
        JSONObject aorbital = obj.getJSONObject("orbital_data");*/
        return asteroid;
    }

    public static Asteroid ParseAsteroid(JSONObject obj) throws JSONException{
        Asteroid asteroid = new Asteroid();
        List<CloseApproachDatum> closeApproachData = new Vector<>();
        Links alinks = new Links();
        EstimatedDiameter aestimatedDiameter = new EstimatedDiameter();
        Kilometers kilometers = new Kilometers();
        Miles miles = new Miles();
        Feet feet = new Feet();
        JSONObject links_asteroid = obj.getJSONObject("links");
        JSONObject estimated_diameter_asteroid = obj.getJSONObject("estimated_diameter");
        JSONObject km = estimated_diameter_asteroid.getJSONObject("kilometers");
        JSONObject ml = estimated_diameter_asteroid.getJSONObject("miles");
        JSONObject ft = estimated_diameter_asteroid.getJSONObject("feet");
        kilometers.setEstimatedDiameterMax(km.getDouble("estimated_diameter_max"));
        kilometers.setEstimatedDiameterMin(km.getDouble("estimated_diameter_min"));
        miles.setEstimatedDiameterMax(ml.getDouble("estimated_diameter_max"));
        miles.setEstimatedDiameterMin(ml.getDouble("estimated_diameter_min"));
        feet.setEstimatedDiameterMax(ft.getDouble("estimated_diameter_max"));
        feet.setEstimatedDiameterMin(ft.getDouble("estimated_diameter_min"));
        aestimatedDiameter.setKilometers(kilometers);
        aestimatedDiameter.setMiles(miles);
        aestimatedDiameter.setFeet(feet);
        JSONArray close_approach_data_asteroid = obj.getJSONArray("close_approach_data");
        for(int j=0; j < close_approach_data_asteroid.length(); j++){
            CloseApproachDatum acloseApproachDatum = new CloseApproachDatum();
            JSONObject object = close_approach_data_asteroid.getJSONObject(j);
            JSONObject miss_distance = object.getJSONObject("miss_distance");
            MissDistance amissDistance = new MissDistance();
            amissDistance.setAstronomical(miss_distance.getString("astronomical"));
            amissDistance.setKilometers(miss_distance.getString("lunar"));
            amissDistance.setLunar(miss_distance.getString("kilometers"));
            amissDistance.setMiles(miss_distance.getString("miles"));
            JSONObject relative_velocity = object.getJSONObject("relative_velocity");
            RelativeVelocity arelativeVelocity = new RelativeVelocity();
            arelativeVelocity.setKilometersPerHour(relative_velocity.getString("kilometers_per_hour"));
            arelativeVelocity.setKilometersPerSecond(relative_velocity.getString("kilometers_per_second"));
            arelativeVelocity.setMilesPerHour(relative_velocity.getString("miles_per_hour"));
            alinks.setSelf(links_asteroid.getString("self"));
            acloseApproachDatum.setCloseApproachDate(object.getString("close_approach_date"));
            acloseApproachDatum.setCloseApproachDateFull(object.getString("close_approach_date_full"));
            acloseApproachDatum.setEpochDateCloseApproach(object.getInt("epoch_date_close_approach"));
            acloseApproachDatum.setMissDistance(amissDistance);
            acloseApproachDatum.setOrbitingBody(object.getString("orbiting_body"));
            acloseApproachDatum.setRelativeVelocity(arelativeVelocity);
            closeApproachData.add(acloseApproachDatum);
        }
        asteroid.setEstimatedDiameter(aestimatedDiameter);
        asteroid.setLinks(alinks);
        asteroid.setCloseApproachData(closeApproachData);
        asteroid.setId(obj.getString("id"));
        asteroid.setName(obj.getString("name"));
        asteroid.setNeoReferenceId(obj.getString("neo_reference_id"));
        asteroid.setNasaJplUrl(obj.getString("nasa_jpl_url"));
        asteroid.setAbsoluteMagnitudeH(obj.getDouble("absolute_magnitude_h"));
        asteroid.setIsPotentiallyHazardousAsteroid(obj.getBoolean("is_potentially_hazardous_asteroid"));
        asteroid.setIsSentryObject(obj.getBoolean("is_sentry_object"));
        return  asteroid;
    }
}
