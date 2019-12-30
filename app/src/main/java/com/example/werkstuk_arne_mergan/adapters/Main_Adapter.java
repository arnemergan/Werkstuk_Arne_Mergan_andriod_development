package com.example.werkstuk_arne_mergan.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.interfaces.OnItemClickListener;
import com.example.werkstuk_arne_mergan.models.Asteroid;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.viewmodels.FollowViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Main_Adapter extends RecyclerView.Adapter< Main_Adapter.ViewHolder>{
    private Context context;
    private List<Asteroid> asteroids;
    private OnItemClickListener listener;
    private Boolean favorite = false;
    private Fragment recycler;

    public Main_Adapter(Context context, OnItemClickListener listener,Fragment recycler) {
        this.context = context;
        this.listener = listener;
        this.recycler = recycler;
        asteroids = new Vector<>();
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public void clearAsteroids(){
        asteroids.clear();
    }

    @NonNull
    @Override
    public Main_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Main_Adapter.ViewHolder holder, int position) {
        if(asteroids.size() > 1) {
            Asteroid asteroid = asteroids.get(position);
            if(asteroid != null){
                holder.bind(asteroid, listener);
                holder.tv_name.setText(asteroid.getName());
                if(asteroid.getCloseApproachData() != null){
                    holder.tv_full_date.setText(getDate(position));
                }
            }
            if(position % 2 != 0){
                holder.view.setBackgroundResource(R.color.even_list);
            }else{
                holder.view.setBackgroundResource(R.color.odd_list);
            }
            setupOnclickFav(holder,asteroid.getId());
        }else{
            holder.tv_name.setText(R.string.nodata);
        }
    }

    public void setupOnclickFav(final ViewHolder holder,final String id){
        final ImageView image = holder.view.findViewById(R.id.fav_main);
        final LinearLayout lin = holder.view.findViewById(R.id.fav_lin_main);
        final FollowViewModel followViewModel = new FollowViewModel(context);
        final TextView follow_text = holder.view.findViewById(R.id.fav_main_text);
        followViewModel.getFollow(id).observe( recycler,new Observer<Follow>() {
            @Override
            public void onChanged(Follow follow) {
                if(follow != null){
                    image.setImageResource(R.mipmap.ic_fav);
                    favorite = true;
                    setupFollowText(follow_text);
                }else{
                    image.setImageResource(R.mipmap.ic_nofav);
                    favorite = false;
                    setupFollowText(follow_text);
                }
            }
        });
        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!favorite){
                    image.setImageResource(R.mipmap.ic_fav);
                    Follow follow = new Follow();
                    follow.setName(holder.tv_name.getText().toString());
                    follow.setId(id);
                    followViewModel.insert(follow);
                    favorite = true;
                    setupFollowText(follow_text);
                }else{
                    image.setImageResource(R.mipmap.ic_nofav);
                    Follow follow = new Follow();
                    follow.setId(id);
                    followViewModel.delete(follow);
                    favorite = false;
                    setupFollowText(follow_text);
                }
            }
        });
    }

    public void setupFollowText(TextView textView){
        if(favorite){
            textView.setText(R.string.unfollow);
        }else{
            textView.setText(R.string.follow);
        }
    }


    public String getDate(int position){
        String date = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = simpleDateFormat.parse(asteroids.get(position).getCloseApproachData().get(0).getCloseApproachDate());
            String date2 = new SimpleDateFormat("dd-MM-yyyy").format(date1);
            date = date2;
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return date;
    }


    @Override
    public int getItemCount() {
        if(asteroids == null){
            return 0;
        }
        return asteroids.size();
    }

    //bron: https://www.codexpedia.com/android/defining-item-click-listener-for-recyclerview-in-android/

    class ViewHolder extends RecyclerView.ViewHolder{
         TextView tv_name;
         TextView tv_full_date;
         View view;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.item_asteroid_name);
            tv_full_date = itemView.findViewById(R.id.item_asteroid_date);
            view = itemView;
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
