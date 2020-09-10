package com.mastanacodes.leaderboard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.mastanacodes.leaderboard.R;
import com.mastanacodes.leaderboard.SkillsModel;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {

    private Context context;
    private List<SkillsModel> skillsModelList;

    public SkillsAdapter(Context context, List<SkillsModel> skillsModelList) {
        this.context = context;
        this.skillsModelList = skillsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SkillsModel skillsModel = skillsModelList.get(position);
        String image = skillsModel.getBadgeUrl();
        String name = skillsModel.getName();
        int score = skillsModel.getScore();
        String country = skillsModel.getCountry();

        holder.setData(image,name,score,country);
    }

    @Override
    public int getItemCount() {
        return skillsModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameTxt;
        private TextView descriptionTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTxt = itemView.findViewById(R.id.name);
            descriptionTxt = itemView.findViewById(R.id.description);
        }

        private void setData(String image, final String name, final int score, final String country) {
            Glide.with(context).load(image).apply(new RequestOptions().placeholder(R.drawable.skill_iq_trimmed)).into(imageView);
            nameTxt.setText(name);
            descriptionTxt.setText(score + " skill IQ Score, " + country);
        }
    }
}
