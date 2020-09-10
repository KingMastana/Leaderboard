package com.mastanacodes.leaderboard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mastanacodes.leaderboard.HoursModel;
import com.mastanacodes.leaderboard.R;

import java.util.ArrayList;
import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.ViewHolder> {

    private List<HoursModel> hoursModelList;
    private Context context;

    public HoursAdapter(Context context, List<HoursModel> hoursModelList) {
        this.context = context;
        this.hoursModelList = hoursModelList;
    }


    @NonNull
    @Override
    public HoursAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursAdapter.ViewHolder holder, int position) {
        final HoursModel hoursModel = hoursModelList.get(position);
        String image = hoursModel.getBadgeUrl();
        String name = hoursModel.getName();
        int hours = hoursModel.getHours();
        String country = hoursModel.getCountry();

        holder.setData(image,name,hours,country);
    }

    @Override
    public int getItemCount() {
        if (hoursModelList != null) {
            return hoursModelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameTxt;
        private TextView descriptionTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            nameTxt = itemView.findViewById(R.id.name);
            descriptionTxt = itemView.findViewById(R.id.description);

        }

        private void setData(String image, final String name, final int hours, final String country) {
            Glide.with(context).load(image).apply(new RequestOptions().placeholder(R.drawable.top_learner)).into(imageView);
            nameTxt.setText(name);
            descriptionTxt.setText(hours + " learning hours, " + country);
        }
    }
}
