package com.example.werkstuk_arne_mergan.room.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EstimatedDiameter_Info {
    @PrimaryKey
    private String id;
    private String diameterKilometer;
    private String diameterMiles;
    private String diameterFeet;
}
