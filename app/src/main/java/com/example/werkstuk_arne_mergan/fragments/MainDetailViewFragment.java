package com.example.werkstuk_arne_mergan.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.adapters.Detail_Adapter;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidCallback;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidsCallback;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;
import com.example.werkstuk_arne_mergan.services.AsteroidParser;

import org.json.JSONException;

//bron: https://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
/**
 * A simple {@link Fragment} subclass.
 */
public class MainDetailViewFragment extends Fragment {
    private RecyclerView recyclerView;

    private Asteroid asteroid = new Asteroid();

    public MainDetailViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_detail_view, container, false);
        recyclerView = v.findViewById(R.id.listview_close_date);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        Intent intent = getActivity().getIntent();
        String id = (String) intent.getStringExtra("asteroid_id");
        LoadAsteroid(id);
        return v;
    }

    private void LoadAsteroid(String id){
        AsteroidRepo.DetailTask task = new AsteroidRepo.DetailTask(new AsteroidCallback() {
            @Override
            public void onTaskCompleted(Asteroid result) throws JSONException {
                asteroid = result;
                setInfo();
        }
        },this.getContext());
        task.execute(id);
    }

    @SuppressLint("SetTextI18n")
    public void setInfo(){
        View v = this.getView();
        Detail_Adapter detail_adapter = new Detail_Adapter(this.getContext(),asteroid.getCloseApproachData());
        recyclerView.setAdapter(detail_adapter);
        TextView text_name = (TextView) v.findViewById(R.id.item_asteroid_name_det);
        text_name.setText(asteroid.getName());
        TextView magnitude = (TextView) v.findViewById(R.id.value_magnitude);
        magnitude.setText(String.valueOf(asteroid.getAbsoluteMagnitudeH()));
        TextView diameter = (TextView) v.findViewById(R.id.value_diameter);
        diameter.setText(asteroid.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin() + " - " + asteroid.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax() + " km");
        TextView hazarduos = (TextView) v.findViewById(R.id.value_hazardous);
        hazarduos.setText(String.valueOf(asteroid.getIsPotentiallyHazardousAsteroid()));
    }

}
