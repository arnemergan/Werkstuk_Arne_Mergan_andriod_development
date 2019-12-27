package com.example.werkstuk_arne_mergan.fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.werkstuk_arne_mergan.adapters.Main_Adapter;
import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.activities.DetailActivity;
import com.example.werkstuk_arne_mergan.interfaces.OnItemClickListener;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.services.Helper;
import com.example.werkstuk_arne_mergan.viewmodels.MainViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainRecyclerViewFragment extends Fragment implements OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemSelectedListener {
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
        //setupSpinner(view);
        setupRecyclerView();
        return view;
    }

    public void setupSpinner(View view){
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.calendar_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void setupRecyclerView(){
        List<Date>dates = new Vector<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dates.add(date);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        dates.add(calendar.getTime());
        loadAsteroids(mainViewModel,this.getContext(),dates);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onItemClick(Asteroid asteroid) {
        Intent intent = new Intent(this.getActivity(),DetailActivity.class);
        intent.putExtra("asteroid_id", asteroid.getId());
        startActivity(intent);
    }

    public void loadAsteroids(MainViewModel mainViewModel, final Context context,List<Date>dates){
        mainViewModel.GetAsteroidList(dates).observe(this, new Observer<List<Asteroid>>() {
            @Override
            public void onChanged(List<Asteroid> asteroids) {
                progressBar.setVisibility(View.INVISIBLE);
                if(!Helper.isConnected(context)){
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context,R.string.chechinternet,duration);
                    toast.show();
                }
                LoadRecyclerview(asteroids);
            }
        });
    }

    public void LoadRecyclerview(List<Asteroid> asteroids){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        main_adapter = new Main_Adapter(this.getActivity(), asteroids,this);
        recyclerView.setAdapter(main_adapter);
        main_adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        List<Date>dates = new Vector<>();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dates.add(date);
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        dates.add(calendar.getTime());
        loadAsteroids(mainViewModel,this.getContext(),dates);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
