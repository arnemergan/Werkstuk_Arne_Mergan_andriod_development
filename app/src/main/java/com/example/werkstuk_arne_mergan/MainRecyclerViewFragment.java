package com.example.werkstuk_arne_mergan;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.werkstuk_arne_mergan.interfaces.OnItemClickListener;
import com.example.werkstuk_arne_mergan.models.Asteroid;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainRecyclerViewFragment extends Fragment implements OnItemClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Asteroid> asteroids;
    private OnItemClickListener listener;
    public MainRecyclerViewFragment() {
        // Required empty public constructor
    }

    private void LoadAsteroids(List<Asteroid>asteroids){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        Main_Adapter adapter = new Main_Adapter(this.getActivity(), asteroids,this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_recycler_view);
        return view;
    }

    @Override
    public void onItemClick(Asteroid asteroid) {
        Intent intent = new Intent(this.getActivity(),DetailActivity.class);
        startActivity(intent);
    }
}
