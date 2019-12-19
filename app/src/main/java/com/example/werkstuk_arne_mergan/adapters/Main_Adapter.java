package com.example.werkstuk_arne_mergan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.activities.MainActivity;
import com.example.werkstuk_arne_mergan.interfaces.OnItemClickListener;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Asteroids;

import java.util.List;

public class Main_Adapter extends RecyclerView.Adapter< Main_Adapter.ViewHolder>{
    private Context context;
    private List<Asteroid> asteroids;
    private OnItemClickListener listener;

    public Main_Adapter(Context context, List<Asteroid> asteroids, OnItemClickListener listener) {
        this.context = context;
        this.asteroids = asteroids;
        this.listener = listener;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    @NonNull
    @Override
    public Main_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Main_Adapter.ViewHolder holder, int position) {
        Asteroid asteroid = asteroids.get(position);
        holder.bind(asteroid,listener);
        holder.tv_name.setText(asteroid.getName());
        String date = asteroid.getCloseApproachData().get(0).getCloseApproachDateFull();
        if(date.isEmpty()){
            holder.tv_full_date.setText(asteroid.getCloseApproachData().get(0).getCloseApproachDate());
        }else{
            holder.tv_full_date.setText(date);
        }
    }

    @Override
    public int getItemCount() {
        return asteroids.size();
    }

    //bron: https://www.codexpedia.com/android/defining-item-click-listener-for-recyclerview-in-android/

    class ViewHolder extends RecyclerView.ViewHolder{
         TextView tv_name;
         TextView tv_full_date;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.item_asteroid_name);
            tv_full_date = itemView.findViewById(R.id.item_asteroid_date);
        }

         void bind(final Asteroid item, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
