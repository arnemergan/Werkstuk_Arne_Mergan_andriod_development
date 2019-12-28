package com.example.werkstuk_arne_mergan.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;
import com.example.werkstuk_arne_mergan.models.Follow;

@Database(entities = {Asteroid.class, Follow.class, CloseApproachDatum.class},version = 1)
public abstract class AsteroidRoomDatabase extends RoomDatabase {
    public abstract AsteroidDao asteroidDao();
    public abstract  FollowDao followDao();
    public static AsteroidRoomDatabase getDatabase(final Context context){
        return Room.databaseBuilder(context.getApplicationContext(),AsteroidRoomDatabase.class,"asteroiddatabase").build();
    }
}
