package com.vedenyapin.alex.githubrepositories.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vedenyapin.alex.githubrepositories.R;
import com.vedenyapin.alex.githubrepositories.model.data.Repo;

import java.util.List;

/**
 * Created by Alex Vedenyapin on 07.03.2017
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
    private List<Repo> mRepos;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mRepoName;
        TextView mRepoStars;
        TextView mRepoLastUpdate;

        ViewHolder(View v) {
            super(v);
            mRepoName = (TextView) v.findViewById(R.id.tvRepoName);
            mRepoStars = (TextView) v.findViewById(R.id.tvRepoStarsCount);
            mRepoLastUpdate = (TextView) v.findViewById(R.id.tvRepoLastUpdate);
        }
    }

    public RepoAdapter(List<Repo> repos) {
        mRepos = repos;
    }

    @Override
    public RepoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mRepoName.setText(mRepos.get(position).getName());
        holder.mRepoStars.setText(String.valueOf(mRepos.get(position).getStarCount()));
        holder.mRepoLastUpdate.setText(mRepos.get(position).getUpdatedAt());
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }
}
