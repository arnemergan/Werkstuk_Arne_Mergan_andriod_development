package com.example.werkstuk_arne_mergan.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.werkstuk_arne_mergan.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(this.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            Toast.makeText(MainActivity.this, " We are in portrait mode", Toast.LENGTH_SHORT).show();
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
        }
        return super.onOptionsItemSelected(item);
    }
}
