package com.example.werkstuk_arne_mergan.room.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class MissDistance_Info {
    @PrimaryKey
    private String id;
    private String kilometers;
    private String lunar;
    private String miles;
    private String astronomical;
}
