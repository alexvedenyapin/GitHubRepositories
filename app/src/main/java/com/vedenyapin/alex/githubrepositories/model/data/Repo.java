package com.vedenyapin.alex.githubrepositories.model.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public class Repo {
    private String name;
    @SerializedName("stargazers_count")
    private int starCount;
    @SerializedName("updated_at")
    private String updatedAt;

    public String getName() {
        return name;
    }

    public void setRepoName(String name) {
        this.name = name;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
