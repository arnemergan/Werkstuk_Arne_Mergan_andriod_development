package com.example.werkstuk_arne_mergan.models;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity
public class RelativeVelocity {
    @SerializedName("kilometers_per_second")
    @Expose
    private String kilometersPerSecond;
    @SerializedName("kilometers_per_hour")
    @Expose
    private String kilometersPerHour;
    @SerializedName("miles_per_hour")
    @Expose
    private String milesPerHour;

    public String getKilometersPerSecond() {
        return kilometersPerSecond;
    }

    public void setKilometersPerSecond(String kilometersPerSecond) {
        this.kilometersPerSecond = kilometersPerSecond;
    }

    public String getKilometersPerHour() {
        return kilometersPerHour;
    }

    public void setKilometersPerHour(String kilometersPerHour) {
        this.kilometersPerHour = kilometersPerHour;
    }

    public String getMilesPerHour() {
        return milesPerHour;
    }

    public void setMilesPerHour(String milesPerHour) {
        this.milesPerHour = milesPerHour;
    }
}
