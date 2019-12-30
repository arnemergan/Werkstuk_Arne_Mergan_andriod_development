package com.example.werkstuk_arne_mergan.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.repositories.FollowRepo;

import java.util.List;

public class FollowViewModel {
    private FollowRepo followRepo;

    public FollowViewModel(Context context) {
        followRepo = new FollowRepo(context);
}

    public LiveData<Follow> getFollow(String id){
        return followRepo.get(id);
    }

    public void insert(Follow follow){
        followRepo.insert(follow);
    }

    public void delete(Follow follow){
        followRepo.delete(follow);
    }

    public LiveData<List<Follow>> getFollows() {
        return followRepo.getFollows();
    }
}
