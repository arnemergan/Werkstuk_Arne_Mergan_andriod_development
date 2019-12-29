package com.example.werkstuk_arne_mergan.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.activities.DetailActivity;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.viewmodels.FollowViewModel;

import java.util.List;

public class Follow_Adapter extends RecyclerView.Adapter<Follow_Adapter.ViewHolder> {
    private Context context;
    private List<Follow> follows;
    private FollowViewModel followViewModel;

    public Follow_Adapter(FollowViewModel followViewModel,Context context) {
        this.followViewModel = followViewModel;
        this.context = context;
    }

    @NonNull
    @Override
    public Follow_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_follow_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Follow_Adapter.ViewHolder holder, int position) {
        final Follow follow = follows.get(position);
        holder.follow_name.setText(follow.getName());
        holder.imageView.setImageResource(R.mipmap.ic_fav);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followViewModel.delete(follow);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("asteroid_id", follow.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(follows == null){
            return 0;
        }
        return follows.size();
    }

    public void setFollows(List<Follow> follows) {
        this.follows = follows;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView follow_name;
        ImageView imageView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            follow_name = itemView.findViewById(R.id.item_follow_asteroid_name);
            imageView = itemView.findViewById(R.id.fav_follow);
        }
    }
}
