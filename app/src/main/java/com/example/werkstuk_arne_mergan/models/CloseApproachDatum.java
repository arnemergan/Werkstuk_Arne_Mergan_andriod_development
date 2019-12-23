package com.example.werkstuk_arne_mergan.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(foreignKeys = @ForeignKey(entity = Asteroid.class,
        parentColumns = "id",
        childColumns = "asteroid_id"))
public class CloseApproachDatum {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @SerializedName("close_approach_date")
    @Expose
    private String closeApproachDate;
    @SerializedName("close_approach_date_full")
    @Expose
    private String closeApproachDateFull;
    @SerializedName("epoch_date_close_approach")
    @Expose
    private Integer epochDateCloseApproach;
    @SerializedName("relative_velocity")
    @Expose
    @Embedded(prefix = "RelativeVelocity_")
    private RelativeVelocity relativeVelocity;
    @SerializedName("miss_distance")
    @Expose
    @Embedded(prefix = "MissDistance_")
    private MissDistance missDistance;
    @SerializedName("orbiting_body")
    @Expose
    private String orbitingBody;

    @ColumnInfo(name = "asteroid_id")
    private String asteroid_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsteroid_id() {
        return asteroid_id;
    }

    public void setAsteroid_id(String asteroid_id) {
        this.asteroid_id = asteroid_id;
    }

    public String getCloseApproachDate() {
        return closeApproachDate;
    }

    public void setCloseApproachDate(String closeApproachDate) {
        this.closeApproachDate = closeApproachDate;
    }

    public String getCloseApproachDateFull() {
        return closeApproachDateFull;
    }

    public void setCloseApproachDateFull(String closeApproachDateFull) {
        this.closeApproachDateFull = closeApproachDateFull;
    }

    public Integer getEpochDateCloseApproach() {
        return epochDateCloseApproach;
    }

    public void setEpochDateCloseApproach(Integer epochDateCloseApproach) {
        this.epochDateCloseApproach = epochDateCloseApproach;
    }

    public RelativeVelocity getRelativeVelocity() {
        return relativeVelocity;
    }

    public void setRelativeVelocity(RelativeVelocity relativeVelocity) {
        this.relativeVelocity = relativeVelocity;
    }

    public MissDistance getMissDistance() {
        return missDistance;
    }

    public void setMissDistance(MissDistance missDistance) {
        this.missDistance = missDistance;
    }

    public String getOrbitingBody() {
        return orbitingBody;
    }

    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }
}
