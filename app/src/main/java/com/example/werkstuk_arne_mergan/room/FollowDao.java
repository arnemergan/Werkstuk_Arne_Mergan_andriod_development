package com.example.werkstuk_arne_mergan.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.werkstuk_arne_mergan.models.Follow;

import java.util.List;

@Dao
public interface FollowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void Insert(Follow follow);

    @Delete
    void Delete(Follow follow);

    @Query("SELECT * FROM follow")
    LiveData<List<Follow>>GetAllFollows();

    @Query("SELECT * FROM follow WHERE id = :id")
    Follow GetFollow(String id);
}
