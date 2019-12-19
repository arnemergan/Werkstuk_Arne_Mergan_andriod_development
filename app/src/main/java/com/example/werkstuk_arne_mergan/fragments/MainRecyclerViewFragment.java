package com.example.werkstuk_arne_mergan.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.werkstuk_arne_mergan.activities.MainActivity;
import com.example.werkstuk_arne_mergan.adapters.Main_Adapter;
import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.activities.DetailActivity;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidsCallback;
import com.example.werkstuk_arne_mergan.interfaces.OnItemClickListener;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;
import com.example.werkstuk_arne_mergan.models.NearEarthObjects;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;
import com.example.werkstuk_arne_mergan.services.AsteroidParser;
import com.example.werkstuk_arne_mergan.viewmodels.MainViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainRecyclerViewFragment extends Fragment implements OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Main_Adapter main_adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        progressBar = view.findViewById(R.id.main_recycler_progress);
        swipeRefreshLayout = view.findViewById(R.id.main_recycler_refresh);
        mainViewModel = new MainViewModel(this.getContext());
        progressBar.setVisibility(View.VISIBLE);
        loadAsteroids(mainViewModel);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onItemClick(Asteroid asteroid) {
        Intent intent = new Intent(this.getActivity(),DetailActivity.class);
        intent.putExtra("asteroid_id", asteroid.getId());
        startActivity(intent);
    }

    public void loadAsteroids(MainViewModel mainViewModel){
        mainViewModel.GetAsteroidList().observe(this, new Observer<List<Asteroid>>() {
            @Override
            public void onChanged(List<Asteroid> asteroids) {
                progressBar.setVisibility(View.INVISIBLE);
                LoadRecyclerview(asteroids);
            }
        });
    }

    private void LoadRecyclerview(List<Asteroid> asteroids){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        main_adapter = new Main_Adapter(this.getActivity(), asteroids,this);
        recyclerView.setAdapter(main_adapter);
        main_adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        loadAsteroids(mainViewModel);
        swipeRefreshLayout.setRefreshing(false);
    }
}
