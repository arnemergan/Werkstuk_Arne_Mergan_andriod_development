package com.example.werkstuk_arne_mergan.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.room.AsteroidRoomDatabase;
import com.example.werkstuk_arne_mergan.room.FollowDao;
import java.util.List;

public class FollowRepo {
    private FollowDao followDao;
    private LiveData<List<Follow>> follows;

    public FollowRepo(Context context) {
        AsteroidRoomDatabase roomDatabase = AsteroidRoomDatabase.getDatabase(context);
        followDao = roomDatabase.followDao();
        follows = followDao.GetAllFollows();
    }

    public void insert(Follow follow){
        new InsertTask(followDao).execute(follow);
    }

    public void delete(Follow follow){
        new DeleteTask(followDao).execute(follow);
    }

    public LiveData<List<Follow>> getFollows(){
        return follows;
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
