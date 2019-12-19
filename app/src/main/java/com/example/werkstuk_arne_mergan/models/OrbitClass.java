package com.example.werkstuk_arne_mergan.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrbitClass {
    @SerializedName("orbit_class_type")
    @Expose
    private String orbitClassType;
    @SerializedName("orbit_class_description")
    @Expose
    private String orbitClassDescription;
    @SerializedName("orbit_class_range")
    @Expose
    private String orbitClassRange;

    public String getOrbitClassType() {
        return orbitClassType;
    }

    public void setOrbitClassType(String orbitClassType) {
        this.orbitClassType = orbitClassType;
    }

    public String getOrbitClassDescription() {
        return orbitClassDescription;
    }

    public void setOrbitClassDescription(String orbitClassDescription) {
        this.orbitClassDescription = orbitClassDescription;
    }

    public String getOrbitClassRange() {
        return orbitClassRange;
    }

    public void setOrbitClassRange(String orbitClassRange) {
        this.orbitClassRange = orbitClassRange;
    }
}
