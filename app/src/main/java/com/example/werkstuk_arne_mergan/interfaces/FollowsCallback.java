package com.example.werkstuk_arne_mergan.interfaces;

import androidx.lifecycle.LiveData;

import com.example.werkstuk_arne_mergan.models.Follow;

import java.util.List;

public interface FollowsCallback {
    public void onTaskCompleted(LiveData<List<Follow>>follows);

}
