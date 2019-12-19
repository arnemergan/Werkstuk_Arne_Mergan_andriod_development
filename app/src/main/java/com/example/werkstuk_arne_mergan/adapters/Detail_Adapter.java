package com.example.werkstuk_arne_mergan.adapters;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;
import com.google.android.material.card.MaterialCardView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//bron : https://abhiandroid.com/ui/listview

public class Detail_Adapter extends RecyclerView.Adapter< Detail_Adapter.ViewHolder>{
    private Context context;
    private List<CloseApproachDatum> closeApproachData;

    public Detail_Adapter(Context context, List<CloseApproachDatum> closeApproachData) {
        this.context = context;
        this.closeApproachData = closeApproachData;
        //this.listener = listener;
    }

    @NonNull
    @Override
    public Detail_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_list,parent,false);
        return new Detail_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Detail_Adapter.ViewHolder holder, int position) {
        CloseApproachDatum closeApproachDatum = closeApproachData.get(position);
        holder.detail_date.setText(closeApproachDatum.getCloseApproachDate());
        holder.detail_body.setText(closeApproachDatum.getOrbitingBody());
        holder.detail_miss.setText(closeApproachDatum.getMissDistance().getKilometers() + " km");
        holder.detail_velo.setText(closeApproachDatum.getRelativeVelocity().getKilometersPerHour() + " km/u");
        holder.detail_days.setText("3 days left");
        switch (closeApproachDatum.getOrbitingBody()){
            case "Merc":
                holder.cardview.setCardBackgroundColor(Color.parseColor("#ff33ff"));
                break;
            case "Venus":
                holder.cardview.setCardBackgroundColor(Color.parseColor("#ffff1a"));
                break;
            case "Earth":
                holder.cardview.setCardBackgroundColor(Color.parseColor("#0066ff"));
                break;
            case "Mars":
                holder.cardview.setCardBackgroundColor(Color.parseColor("#ff3300"));
                break;
            default:

        }
    }

    @Override
    public int getItemCount() {
        return closeApproachData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView detail_date;
        CardView cardview;
        TextView detail_days;
        TextView detail_velo;
        TextView detail_miss;
        TextView detail_body;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardview = itemView.findViewById(R.id.cardview_item_detail);
            detail_date = itemView.findViewById(R.id.item_detail_date);
            detail_days = itemView.findViewById(R.id.item_detail_days);
            detail_body = itemView.findViewById(R.id.item_detail_body);
            detail_velo = itemView.findViewById(R.id.item_detail_velocity);
            detail_miss = itemView.findViewById(R.id.item_detail_missdistance);
        }
    }
}
