package com.example.werkstuk_arne_mergan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.fragments.MainDetailViewFragment;
import com.example.werkstuk_arne_mergan.fragments.MainRecyclerViewFragment;
import com.example.werkstuk_arne_mergan.interfaces.OnItemClickListener;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;
import com.example.werkstuk_arne_mergan.services.Helper;
import com.example.werkstuk_arne_mergan.viewmodels.MainViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private boolean twopane = false;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.fragment_detail) != null) {
            twopane = true;
        }
        if(twopane){
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_list, new MainRecyclerViewFragment(fragmentManager,twopane))
                    .commit();
        }
        androidx.appcompat.widget.Toolbar mlt = findViewById(R.id.main_bar);
        setSupportActionBar(mlt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
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
}
