package com.example.werkstuk_arne_mergan;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.werkstuk_arne_mergan.remote.AsteroidService;
import com.example.werkstuk_arne_mergan.remote.AsteroidsUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainDetailViewFragment extends Fragment {

    public MainDetailViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_detail_view, container, false);
        return v;
    }

}
