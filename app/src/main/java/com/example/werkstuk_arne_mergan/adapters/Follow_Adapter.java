package com.example.werkstuk_arne_mergan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.viewmodels.FollowViewModel;

import java.util.List;

public class Follow_Adapter extends RecyclerView.Adapter<Follow_Adapter.ViewHolder> {
    private Context context;
    private List<Follow> follows;
    private FollowViewModel followViewModel;

    public Follow_Adapter(FollowViewModel followViewModel) {
        this.followViewModel = followViewModel;
    }

    @NonNull
    @Override
    public Follow_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_follow_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Follow_Adapter.ViewHolder holder, int position) {
        final Follow follow = new Follow();
        holder.follow_name.setText(follow.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followViewModel.delete(follow);
            }
        });
    }

    @Override
    public int getItemCount() {
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
