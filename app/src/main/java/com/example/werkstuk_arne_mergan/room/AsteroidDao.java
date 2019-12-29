package com.example.werkstuk_arne_mergan.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;

import java.util.List;

@Dao
public interface AsteroidDao {
    @Query("SELECT * FROM asteroid")
    LiveData<List<Asteroid>> GetAllAsteroids();

    @Query("SELECT * FROM asteroid WHERE id = :id")
    LiveData<Asteroid> GetAsteroid(String id);

    @Query("SELECT * FROM asteroid WHERE id = :id")
    Asteroid GetAsteroidCheck(String id);

    @Query("SELECT * FROM closeapproachdatum WHERE asteroid_id = :id LIMIT 1")
    LiveData<List<CloseApproachDatum>> GetCloseApproachData(String id);

    @Update
    void Update(Asteroid asteroid, List<CloseApproachDatum> closeApproachData);

    @Update
    void Update(Asteroid asteroid);

    @Delete
    void Delete(Asteroid asteroid);

    @Query("DELETE FROM CloseApproachDatum WHERE asteroid_id = :id")
    void DeleteCloseApproachData(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(Asteroid asteroid);

    @Insert
    void Insert(List<CloseApproachDatum> closeApproachData);
}
