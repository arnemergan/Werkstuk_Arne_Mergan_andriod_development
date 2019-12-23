package com.example.werkstuk_arne_mergan.models;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Asteroids {
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("element_count")
    @Expose
    private Integer elementCount;
    @SerializedName("near_earth_objects")
    @Expose
    private List<Asteroid> nearEarthObjects;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getElementCount() {
        return elementCount;
    }

    public void setElementCount(Integer elementCount) {
        this.elementCount = elementCount;
    }

    public List<Asteroid> getNearEarthObjects() {
        return nearEarthObjects;
    }

    public void setNearEarthObjects(List<Asteroid> nearEarthObjects) {
        this.nearEarthObjects = nearEarthObjects;
    }
}
