package com.example.werkstuk_arne_mergan.room.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName= "closeaproachdata_table", foreignKeys= {@ForeignKey(entity = RelativeVelocity_Info.class, parentColumns= "id",childColumns= "relativevelocity_id"),@ForeignKey(entity = MissDistance_Info.class,parentColumns = "id",childColumns = "missdistance_id"),@ForeignKey(entity = EstimatedDiameter_Info.class,parentColumns = "id",childColumns = "estimateddiameter_id")})
public class CloseApproachData_Info {
    @PrimaryKey
    private UUID id;
    private String closeApproachDate;
    private String closeApproachDateFull;
    private Integer epochDateCloseApproach;
    private String orbitingBody;
    @ColumnInfo(name = "relativevelocity_id")
    private String relativevelocity_id;
    @ColumnInfo(name = "missdistance_id")
    private String missdistance_id;
    @ColumnInfo(name = "estimateddiameter_id")
    private String estimateddiameter_id;
}
