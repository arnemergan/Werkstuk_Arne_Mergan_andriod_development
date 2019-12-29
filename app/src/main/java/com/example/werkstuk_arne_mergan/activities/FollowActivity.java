package com.example.werkstuk_arne_mergan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.adapters.Follow_Adapter;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.viewmodels.FollowViewModel;

import java.util.List;

public class FollowActivity extends AppCompatActivity {

    private FollowViewModel followViewModel;
    private RecyclerView recyclerView;
    private Follow_Adapter follow_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        Toolbar maintoolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(maintoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);
        followViewModel = new FollowViewModel(this);
        follow_adapter = new Follow_Adapter(followViewModel,this);
        recyclerView = findViewById(R.id.follow_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(follow_adapter);
        followViewModel.getFollows().observe(this ,new Observer<List<Follow>>() {
            @Override
            public void onChanged(List<Follow> follows) {
                follow_adapter.setFollows(follows);
                follow_adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            //TODO action settings;
            Intent settings_intent = new Intent(this, SettingsActivity.class);
            startActivity(settings_intent);
            return true;
        }else if(item.getItemId() == R.id.action_favorites){
            Intent follow_intent = new Intent(this, FollowActivity.class);
            startActivity(follow_intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
