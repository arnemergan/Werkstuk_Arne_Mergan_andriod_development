package com.example.werkstuk_arne_mergan.adapters;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.werkstuk_arne_mergan.R;
import com.example.werkstuk_arne_mergan.models.CloseApproachDatum;
import com.example.werkstuk_arne_mergan.models.Follow;
import com.example.werkstuk_arne_mergan.services.LocaleHelper;
import com.example.werkstuk_arne_mergan.viewmodels.FollowViewModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//bron : https://abhiandroid.com/ui/listview

public class Detail_Adapter extends RecyclerView.Adapter< Detail_Adapter.ViewHolder>{
    private Context context;
    private List<CloseApproachDatum> closeApproachData;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

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
        if(closeApproachData.size() > 1) {
            CloseApproachDatum closeApproachDatum = closeApproachData.get(position);
            holder.detail_body.setText(closeApproachDatum.getOrbitingBody());
            Double missdistance = Double.parseDouble(closeApproachDatum.getMissDistance().getKilometers());
            Double velocity = Double.parseDouble(closeApproachDatum.getRelativeVelocity().getKilometersPerHour());
            holder.detail_miss.setText(decimalFormat.format(missdistance) + " km");
            holder.detail_velo.setText(decimalFormat.format(velocity) + " km/u");
            try {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = calendar.getTime();
                Date date1 = simpleDateFormat.parse(closeApproachDatum.getCloseApproachDate());
                long diff = date1.getTime() - date.getTime();
                long days = diff / (1000 * 60 * 60 * 24);
                if(days < 0){
                    holder.detail_days.setText(Math.abs(days) + " " + context.getString(R.string.days_ago));
                }else{
                    if(LocaleHelper.getLanguage(context).equals("en")) {
                        holder.detail_days.setText(days + " " + context.getString(R.string.days_left));
                    }else{
                        holder.detail_days.setText(context.getString(R.string.binnen)+ days + " " + context.getString(R.string.days_left));
                    }
                }
                String date2 = new SimpleDateFormat("dd-MM-yyyy").format(date1);
                holder.detail_date.setText(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            switch (closeApproachDatum.getOrbitingBody()) {
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
        }else{
            holder.detail_date.setText(R.string.nodata);
            holder.detail_body.setVisibility(View.INVISIBLE);
            holder.detail_miss.setVisibility(View.INVISIBLE);
            holder.detail_velo.setVisibility(View.INVISIBLE);
            holder.detail_days.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        if(closeApproachData == null){
            return 0;
        }
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
