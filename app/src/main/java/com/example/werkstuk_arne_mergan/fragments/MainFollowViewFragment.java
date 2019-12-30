package com.example.werkstuk_arne_mergan.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.adapters.Follow_Adapter;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.services.Helper;
import com.example.werkstuk_arne_mergan.viewmodels.FollowViewModel;

import java.util.List;

public class MainFollowViewFragment extends Fragment {
    private FollowViewModel followViewModel;
    private RecyclerView recyclerView;
    private Follow_Adapter follow_adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout = v.findViewById(R.id.follow_recycler_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupFollowFragment();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        setupFollowFragment();
        return v;
    }

    public void setupFollowFragment(){
        follow_adapter.notifyDataSetChanged();
        follow_adapter.clear();
        followViewModel.getFollows().observe(this, new Observer<List<Follow>>(){
            @Override
            public void onChanged(List<Follow> follows) {
                follow_adapter.setFollows(follows);
                follow_adapter.notifyDataSetChanged();
            }
        });
        if(!Helper.isConnected(getContext())){
            Toast toast = Toast.makeText(getContext(),R.string.chechinternet,Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
