package com.example.werkstuk_arne_mergan.interfaces;

import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Follow;

import java.util.List;

public interface FollowCallback {
    public void onTaskCompleted(List<Follow>follows);

}
