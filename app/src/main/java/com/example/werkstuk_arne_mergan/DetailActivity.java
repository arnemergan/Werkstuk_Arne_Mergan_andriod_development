package com.example.werkstuk_arne_mergan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.remote.AsteroidService;
import com.example.werkstuk_arne_mergan.remote.AsteroidsUtils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private AsteroidService asteroidService;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar maintoolbar = (Toolbar) findViewById(R.id.det_bar);
        setSupportActionBar(maintoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        asteroidService = AsteroidsUtils.GetAsteroidService();
        setInfo();
    }

    public void setInfo(){
        asteroidService.GetAsteroid().enqueue(new Callback<Asteroid>() {
            @Override
            public void onResponse(Call<Asteroid> call, Response<Asteroid> response) {

                if(response.isSuccessful()) {
                    TextView text_name = (TextView) findViewById(R.id.item_asteroid_name_det);
                    TextView text_date = (TextView) findViewById(R.id.item_asteroid_date_det);

                    text_name.setText(response.body().getName());
                    text_date.setText(response.body().getCloseApproachData().toString());
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<Asteroid> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");

            }
        /*TextView text_name = (TextView) findViewById(R.id.item_asteroid_name_det);
        TextView text_date = (TextView) findViewById(R.id.item_asteroid_date_det);

        Intent intent = getIntent();
        Asteroid asteroid = (Asteroid) intent.getSerializableExtra("Asteroid");
        text_name.setText(asteroid.getName());
        text_date.setText(asteroid.getClosest_approach_date().toString());*/
    });
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
            Intent settings_intent = new Intent(this,SettingsActivity.class);
            startActivity(settings_intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
