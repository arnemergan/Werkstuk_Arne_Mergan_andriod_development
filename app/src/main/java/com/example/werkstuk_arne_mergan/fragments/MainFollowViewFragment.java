package com.example.werkstuk_arne_mergan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.adapters.Follow_Adapter;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.viewmodels.FollowViewModel;

import java.util.List;

public class MainFollowViewFragment extends Fragment {
    private FollowViewModel followViewModel;
    private RecyclerView recyclerView;
    private Follow_Adapter follow_adapter;

    public static MainFollowViewFragment newInstance(){
        MainFollowViewFragment fragment = new MainFollowViewFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_main_follow_view, container, false);
        recyclerView = v.findViewById(R.id.follow_recycler_view);
        followViewModel = new FollowViewModel(this.getContext());
        follow_adapter = new Follow_Adapter(followViewModel,this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(follow_adapter);
        followViewModel.getFollows().observe(this ,new Observer<List<Follow>>() {
            @Override
            public void onChanged(List<Follow> follows) {
                follow_adapter.setFollows(follows);
                follow_adapter.notifyDataSetChanged();
            }
        });
        return v;
    }

    public void setupFollowFragment(){
        followViewModel.getFollows().observe(this ,new Observer<List<Follow>>() {
            @Override
            public void onChanged(List<Follow> follows) {
                follow_adapter.setFollows(follows);
                follow_adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setupFollowFragment();
    }
}
