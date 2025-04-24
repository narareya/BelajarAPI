package com.example.belajarapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private List<TeamModel> teamModelList;
    private Context context;

    public TeamAdapter(Context context, List<TeamModel> teamModelList) {
        this.context = context;
        this.teamModelList = teamModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeamModel team = teamModelList.get(position);
        holder.teamName.setText(team.getStrTeam());
        holder.teamStadium.setText(team.getStrStadium());
        Glide.with(context).load(((TeamModel) team).getStrBadge()).into(holder.teamBadge);

    }

    @Override
    public int getItemCount() {
        return teamModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        TextView teamStadium;
        ImageView teamBadge;

        public ViewHolder(View view) {
            super(view);
            teamName = view.findViewById(R.id.tvNamaTim);
            teamBadge = view.findViewById(R.id.ivBadgeTeam);
            teamStadium = view.findViewById(R.id.tvStadium);
        }
    }
}
