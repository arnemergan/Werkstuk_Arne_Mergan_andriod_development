package com.example.werkstuk_arne_mergan.room.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RelativeVelocity_Info {
    @PrimaryKey
    private String id;
    private String kilometersPerSecond;
    private String kilometersPerHour;
    private String milesPerHour;

}

