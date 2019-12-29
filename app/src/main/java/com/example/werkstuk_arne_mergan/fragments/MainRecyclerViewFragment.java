package com.example.werkstuk_arne_mergan.fragments;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.werkstuk_arne_mergan.adapters.Main_Adapter;
import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.activities.DetailActivity;
import com.example.werkstuk_arne_mergan.interfaces.OnItemClickListener;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.viewmodels.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainRecyclerViewFragment extends Fragment implements OnItemClickListener {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Main_Adapter main_adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainViewModel mainViewModel;
    private List<Date>dates = new Vector<>();
    private FragmentManager fragmentManager;
    private TextView textdate;
    private LinearLayoutManager linearLayoutManager;
    private boolean twopane = false;
    private SimpleDateFormat simpleDateFormatEng = new SimpleDateFormat("yyyy-MM-dd");

    public MainRecyclerViewFragment() {
    }

    public MainRecyclerViewFragment(FragmentManager fragmentManager,Boolean twopane) {
        this.fragmentManager = fragmentManager;
        this.twopane = twopane;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list_view, container, false);
        textdate = view.findViewById(R.id.date_recycler);
        setupDates();
        mainViewModel = new MainViewModel(getContext(),dates);
        main_adapter = new Main_Adapter(getContext(),this,this);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(main_adapter);
        progressBar = view.findViewById(R.id.main_recycler_progress);
        swipeRefreshLayout = view.findViewById(R.id.main_recycler_refresh);
        mainViewModel = new MainViewModel(this.getContext(),dates);
        setupRecyclerView();
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

    public void setupRecyclerView(){
        main_adapter.clearAsteroids();
        main_adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.VISIBLE);
        mainViewModel.GetAsteroidList(dates).observe(this, new Observer<List<Asteroid>>(){
            @Override
            public void onChanged(List<Asteroid> asteroids) {
                main_adapter.setAsteroids(asteroids);
                main_adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void setupDates(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        dates.add(date);
        textdate.setText(simpleDateFormatEng.format(date));
        for (int i = -1;i>-7;i--){
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, i);
            dates.add(calendar.getTime());
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

    @Override
    public void onResume() {
        super.onResume();
        setupRecyclerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        setupRecyclerView();
    }
}
