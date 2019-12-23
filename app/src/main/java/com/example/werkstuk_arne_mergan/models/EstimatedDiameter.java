package com.example.werkstuk_arne_mergan.models;

import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class EstimatedDiameter {
    @SerializedName("kilometers")
    @Expose
    @Embedded(prefix = "km_")
    private Kilometers kilometers;
    @SerializedName("meters")
    @Expose
    @Embedded(prefix = "me_")
    private Meters meters;
    @SerializedName("miles")
    @Expose
    @Embedded(prefix = "mi_")
    private Miles miles;
    @SerializedName("feet")
    @Expose
    @Embedded(prefix = "fe_")
    private Feet feet;

    public Kilometers getKilometers() {
        return kilometers;
    }

    public void setKilometers(Kilometers kilometers) {
        this.kilometers = kilometers;
    }

    public Meters getMeters() {
        return meters;
    }

    public void setMeters(Meters meters) {
        this.meters = meters;
    }

    public Miles getMiles() {
        return miles;
    }

    public void setMiles(Miles miles) {
        this.miles = miles;
    }

    public Feet getFeet() {
        return feet;
    }

    public void setFeet(Feet feet) {
        this.feet = feet;
    }
}
