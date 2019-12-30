package com.example.werkstuk_arne_mergan.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.werkstuk_arne_mergan.interfaces.FollowCallback;
import com.example.werkstuk_arne_mergan.interfaces.FollowsCallback;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.room.AsteroidRoomDatabase;
import com.example.werkstuk_arne_mergan.room.FollowDao;
import java.util.List;

public class FollowRepo {
    private FollowDao followDao;
    private MutableLiveData<Follow> followMutableLiveData = new MutableLiveData<>();

    public FollowRepo(Context context) {
        AsteroidRoomDatabase roomDatabase = AsteroidRoomDatabase.getDatabase(context);
        followDao = roomDatabase.followDao();
    }

    public LiveData<Follow> get(String id){
        new GetTask(followDao, new FollowCallback() {
            @Override
            public void onTaskCompleted(Follow follow) {
                followMutableLiveData.postValue(follow);
            }
        }).execute(id);
        return followMutableLiveData;
    }

    public void insert(Follow follow){
        new InsertTask(followDao).execute(follow);
    }

    public void delete(Follow follow){
        new DeleteTask(followDao).execute(follow);
    }

    public LiveData<List<Follow>> getFollows(){
        return followDao.GetAllFollows();
    }

    public static class GetTask extends AsyncTask<String, Void, Follow> {
        private FollowDao followDao;
        private FollowCallback followCallback;

        public GetTask(FollowDao followDao,FollowCallback followCallback) {
            this.followDao = followDao;
            this.followCallback = followCallback;
        }

        @Override
        protected Follow doInBackground(String... strings) {
            return followDao.GetFollow(strings[0]);
        }

        @Override
        protected void onPostExecute(Follow follow) {
            super.onPostExecute(follow);
            followCallback.onTaskCompleted(follow);
        }
    }

    public static class InsertTask extends AsyncTask<Follow, Void, Void> {
        private FollowDao followDao;

        public InsertTask(FollowDao followDao) {
            this.followDao = followDao;
        }

        @Override
        protected Void doInBackground(Follow... follows) {
            followDao.Insert(follows[0]);
            return null;
        }
    }

    public static class DeleteTask extends AsyncTask<Follow, Void, Void> {
        private FollowDao followDao;

        public DeleteTask(FollowDao followDao) {
            this.followDao = followDao;
        }

        @Override
        protected Void doInBackground(Follow... follows) {
            followDao.Delete(follows[0]);
            return null;
        }
    }
}
