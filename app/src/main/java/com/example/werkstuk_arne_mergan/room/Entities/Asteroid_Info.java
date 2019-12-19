package com.example.werkstuk_arne_mergan.room.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;

import java.util.List;
@Entity(foreignKeys = {@ForeignKey(entity = CloseApproachData_Info.class, parentColumns= "id",childColumns= "closeapproachdata_id"),@ForeignKey(entity = CloseApproachData_Info.class, parentColumns= "id",childColumns= "estimateddiameter_id")})
public class Asteroid_Info {
    @PrimaryKey
    private String id;
    private Double absoluteMagnitudeH;
    private List<CloseApproachDatum> closeApproachData;
    private String name;
    private Boolean isPotentiallyHazardousAsteroid;
    @ColumnInfo(name = "estimateddiameter_id")
    private String estimateddiameter_id;
}
