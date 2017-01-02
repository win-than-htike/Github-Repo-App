package com.example.ice_pc.github;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ICE-PC on 1/1/2017.
 */
public class GithubRepo {

    @SerializedName("name")
    String name;

    @SerializedName("svn_url")
    String url;

    public GithubRepo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return(name + " " +  url);
    }
}
