package com.example.werkstuk_arne_mergan.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.activities.MainActivity;
import com.example.werkstuk_arne_mergan.adapters.Detail_Adapter;
import com.example.werkstuk_arne_mergan.adapters.Main_Adapter;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidCallback;
import com.example.werkstuk_arne_mergan.interfaces.AsteroidsCallback;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.repositories.AsteroidRepo;
import com.example.werkstuk_arne_mergan.services.AsteroidParser;
import com.example.werkstuk_arne_mergan.services.Helper;
import com.example.werkstuk_arne_mergan.viewmodels.DetailViewModel;
import com.example.werkstuk_arne_mergan.viewmodels.FollowViewModel;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//bron: https://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
/**
 * A simple {@link Fragment} subclass.
 */
public class MainDetailViewFragment extends Fragment {
    private RecyclerView recyclerView;
    private DetailViewModel detailViewModel;
    private Asteroid asteroid = new Asteroid();
    private ProgressBar progressBar;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private String id = null;
    private Boolean favorite = false;

    public MainDetailViewFragment() {
        // Required empty public constructor
    }

    public MainDetailViewFragment(String id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_detail_view, container, false);
        recyclerView = v.findViewById(R.id.listview_close_date);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        Intent intent = getActivity().getIntent();
        progressBar = v.findViewById(R.id.detail_progress);
        if(id == null) {
            id = (String) intent.getStringExtra("asteroid_id");
        }
        detailViewModel = new DetailViewModel(this.getContext());
        progressBar.setVisibility(View.VISIBLE);
        LoadAsteroid(id);
        if(!Helper.isConnected(getContext())){
            Toast toast = Toast.makeText(getContext(),R.string.chechinternet,Toast.LENGTH_LONG);
            toast.show();
        }
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        id = null;
    }

    private void LoadAsteroid(String id){
        detailViewModel.GetAsteroid(id).observe(this, new Observer<Asteroid>() {
            @Override
            public void onChanged(Asteroid newasteroid) {
                progressBar.setVisibility(View.INVISIBLE);
                asteroid = newasteroid;
                if(asteroid == null){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getContext(),R.string.chechinternet,duration);
                    toast.show();
                    startActivity(intent);
                }
                setInfo();
            }
        });
    }

    public void setupBtn(Button btn){
        if(favorite){
            btn.setBackgroundColor(getResources().getColor(R.color.odd_list));
            btn.setText(R.string.unfollow);
        }else{
            btn.setBackgroundColor(getResources().getColor(R.color.toolbar_text));
            btn.setText(R.string.follow);
        }
    }

    public void setupOnclickFav(final View view){
        final Button btn = view.findViewById(R.id.btn_follow);
        final FollowViewModel followViewModel = new FollowViewModel(this.getContext());
        followViewModel.getFollow(id).observe( this,new Observer<Follow>() {
            @Override
            public void onChanged(Follow follow) {
                if(follow != null){
                    favorite = true;
                    setupBtn(btn);
                }else{
                    favorite = false;
                    setupBtn(btn);
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!favorite){
                    Follow follow = new Follow();
                    follow.setName(asteroid.getName());
                    follow.setId(asteroid.getId());
                    followViewModel.insert(follow);
                    favorite = true;
                    setupBtn(btn);
                }else{
                    Follow follow = new Follow();
                    follow.setId(asteroid.getId());
                    followViewModel.delete(follow);
                    favorite = false;
                    setupBtn(btn);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void setInfo(){
        View v = this.getView();
        setupOnclickFav(v);
        Detail_Adapter detail_adapter = new Detail_Adapter(this.getContext(),asteroid.getCloseApproachData());
        recyclerView.setAdapter(detail_adapter);
        TextView text_name = (TextView) v.findViewById(R.id.item_asteroid_name_det);
        text_name.setText(asteroid.getName());
        TextView magnitude = (TextView) v.findViewById(R.id.value_magnitude);
        magnitude.setText(String.valueOf(decimalFormat.format(asteroid.getAbsoluteMagnitudeH())));
        TextView diameter = (TextView) v.findViewById(R.id.value_diameter);
        diameter.setText(decimalFormat.format(asteroid.getEstimatedDiameter().getKilometers().getEstimatedDiameterMin())+ " - " + decimalFormat.format(asteroid.getEstimatedDiameter().getKilometers().getEstimatedDiameterMax()) + " km");
        TextView hazarduos = (TextView) v.findViewById(R.id.value_hazardous);
        hazarduos.setText(String.valueOf(asteroid.getIsPotentiallyHazardousAsteroid()));
    }

}
