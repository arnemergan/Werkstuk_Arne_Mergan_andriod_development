package com.example.werkstuk_arne_mergan.fragments;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.werkstuk_arne_mergan.adapters.Main_Adapter;
import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.activities.DetailActivity;
import com.example.werkstuk_arne_mergan.interfaces.OnItemClickListener;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.services.Helper;
import com.example.werkstuk_arne_mergan.viewmodels.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainRecyclerViewFragment extends Fragment implements OnItemClickListener {
    private RecyclerView recyclerView;
    private Main_Adapter main_adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainViewModel mainViewModel;
    private List<Date>dates = new Vector<>();
    private FragmentManager fragmentManager;
    private TextView textdate;
    private LinearLayoutManager linearLayoutManager;
    private boolean twopane = false;
    private SimpleDateFormat simpleDateFormatEng = new SimpleDateFormat("yyyy-MM-dd");
    private Boolean recent = false;

    public MainRecyclerViewFragment() {
    }

    public MainRecyclerViewFragment(FragmentManager fragmentManager,Boolean twopane) {
        this.fragmentManager = fragmentManager;
        this.twopane = twopane;
    }

    public MainRecyclerViewFragment(Boolean recent){
        this.recent = recent;
    }

    public static MainRecyclerViewFragment newInstance(boolean recent){
        MainRecyclerViewFragment fragment = new MainRecyclerViewFragment(recent);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list_view, container, false);
        textdate = view.findViewById(R.id.date_recycler);
        setupDates();
        main_adapter = new Main_Adapter(getContext(),this,this);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(main_adapter);
        swipeRefreshLayout = view.findViewById(R.id.main_recycler_refresh);
        mainViewModel = new MainViewModel(this.getActivity().getApplication(),dates);
        mainViewModel.GetAsteroidList().observe(this, new Observer<List<Asteroid>>(){
            @Override
            public void onChanged(List<Asteroid> asteroids) {
                main_adapter.setAsteroids(asteroids);
                main_adapter.notifyDataSetChanged();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupRecyclerView();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = linearLayoutManager.findFirstVisibleItemPosition();
                if(main_adapter.getDate(position) != "") {
                    textdate.setText(main_adapter.getDate(position));
                }else {
                    textdate.setText(simpleDateFormatEng.format(dates.get(0)));
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        observe();
        super.onStart();
    }

    @Override
    public void onResume() {
        observe();
        super.onResume();
    }

    public void observe(){
        mainViewModel.GetAsteroidList().observe(this, new Observer<List<Asteroid>>(){
            @Override
            public void onChanged(List<Asteroid> asteroids) {
                main_adapter.setAsteroids(asteroids);
                main_adapter.notifyDataSetChanged();
            }
        });
    }

    public void setupRecyclerView(){
        observe();
        if(!Helper.isConnected(getContext())){
            Toast toast = Toast.makeText(getContext(),R.string.chechinternet,Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void setupDates(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dates.clear();
        if(recent){
            dates.add(date);
            textdate.setText(simpleDateFormatEng.format(date));
            for (int i = -1;i>-5;i--){
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_YEAR, i);
                dates.add(calendar.getTime());
            }
        }else{
            textdate.setText(simpleDateFormatEng.format(date));
            for (int i = 1;i<7;i++){
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_YEAR, i);
                dates.add(calendar.getTime());
            }
        }
    }


    @Override
    public void onItemClick(Asteroid asteroid) {
        if(!twopane) {
            Intent intent = new Intent(this.getActivity(), DetailActivity.class);
            intent.putExtra("asteroid_id", asteroid.getId());
            startActivity(intent);
        }else {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_detail, new MainDetailViewFragment(asteroid.getId()))
                    .commit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        twopane = false;
    }
}
