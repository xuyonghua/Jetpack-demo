package com.xuyonghua.paging.entity;

import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("full_name")
    private String fullName;
    private String description;
    @SerializedName("stargazers_count")
    private String starCount;
    @SerializedName("forks_count")
    private String forkCount;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarCount() {
        return starCount;
    }

    public void setStarCount(String starCount) {
        this.starCount = starCount;
    }

    public String getForkCount() {
        return forkCount;
    }

    public void setForkCount(String forkCount) {
        this.forkCount = forkCount;
    }
}
