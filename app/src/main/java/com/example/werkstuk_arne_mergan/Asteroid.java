package com.example.werkstuk_arne_mergan;

import java.util.Date;

public class Asteroid {
    private Integer id;
    private String name;
    private Boolean hazardous;
    private Double diameter_min_km;
    private Double diameter_max_km;
    private Double diameter_min_miles;
    private Double diameter_max_miles;
    private Double diameter_min_feet;
    private Double diameter_max_feet;
    private Date closest_approach_date;
    private Double speed_km_sec;
    private Double speed_km_hour;
    private Double speed_miles_hour;
    private String nasaurl;
    private Double magnitude;
    private Double miss_distance_km;
    private Double miss_distance_miles;

    public Asteroid() {
    }

    public Asteroid(Integer id, String name, Boolean hazardous, Double diameter_min_km, Double diameter_max_km, Double diameter_min_miles, Double diameter_max_miles, Double diameter_min_feet, Double diameter_max_feet, Date closest_approach_date, Double speed_km_sec, Double speed_km_hour, Double speed_miles_hour, String nasaurl, Double magnitude, Double miss_distance_km, Double miss_distance_miles) {
        this.id = id;
        this.name = name;
        this.hazardous = hazardous;
        this.diameter_min_km = diameter_min_km;
        this.diameter_max_km = diameter_max_km;
        this.diameter_min_miles = diameter_min_miles;
        this.diameter_max_miles = diameter_max_miles;
        this.diameter_min_feet = diameter_min_feet;
        this.diameter_max_feet = diameter_max_feet;
        this.closest_approach_date = closest_approach_date;
        this.speed_km_sec = speed_km_sec;
        this.speed_km_hour = speed_km_hour;
        this.speed_miles_hour = speed_miles_hour;
        this.nasaurl = nasaurl;
        this.magnitude = magnitude;
        this.miss_distance_km = miss_distance_km;
        this.miss_distance_miles = miss_distance_miles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHazardous() {
        return hazardous;
    }

    public void setHazardous(Boolean hazardous) {
        this.hazardous = hazardous;
    }

    public Double getDiameter_min_km() {
        return diameter_min_km;
    }

    public void setDiameter_min_km(Double diameter_min_km) {
        this.diameter_min_km = diameter_min_km;
    }

    public Double getDiameter_max_km() {
        return diameter_max_km;
    }

    public void setDiameter_max_km(Double diameter_max_km) {
        this.diameter_max_km = diameter_max_km;
    }

    public Double getDiameter_min_miles() {
        return diameter_min_miles;
    }

    public void setDiameter_min_miles(Double diameter_min_miles) {
        this.diameter_min_miles = diameter_min_miles;
    }

    public Double getDiameter_max_miles() {
        return diameter_max_miles;
    }

    public void setDiameter_max_miles(Double diameter_max_miles) {
        this.diameter_max_miles = diameter_max_miles;
    }

    public Double getDiameter_min_feet() {
        return diameter_min_feet;
    }

    public void setDiameter_min_feet(Double diameter_min_feet) {
        this.diameter_min_feet = diameter_min_feet;
    }

    public Double getDiameter_max_feet() {
        return diameter_max_feet;
    }

    public void setDiameter_max_feet(Double diameter_max_feet) {
        this.diameter_max_feet = diameter_max_feet;
    }

    public Date getClosest_approach_date() {
        return closest_approach_date;
    }

    public void setClosest_approach_date(Date closest_approach_date) {
        this.closest_approach_date = closest_approach_date;
    }

    public Double getSpeed_km_sec() {
        return speed_km_sec;
    }

    public void setSpeed_km_sec(Double speed_km_sec) {
        this.speed_km_sec = speed_km_sec;
    }

    public Double getSpeed_km_hour() {
        return speed_km_hour;
    }

    public void setSpeed_km_hour(Double speed_km_hour) {
        this.speed_km_hour = speed_km_hour;
    }

    public Double getSpeed_miles_hour() {
        return speed_miles_hour;
    }

    public void setSpeed_miles_hour(Double speed_miles_hour) {
        this.speed_miles_hour = speed_miles_hour;
    }

    public String getNasaurl() {
        return nasaurl;
    }

    public void setNasaurl(String nasaurl) {
        this.nasaurl = nasaurl;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public Double getMiss_distance_km() {
        return miss_distance_km;
    }

    public void setMiss_distance_km(Double miss_distance_km) {
        this.miss_distance_km = miss_distance_km;
    }

    public Double getMiss_distance_miles() {
        return miss_distance_miles;
    }

    public void setMiss_distance_miles(Double miss_distance_miles) {
        this.miss_distance_miles = miss_distance_miles;
    }
}
