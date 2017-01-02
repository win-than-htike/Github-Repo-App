package com.example.ice_pc.github;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ICE-PC on 1/1/2017.
 */
public interface GithubApi {

    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> getRepos(@Path("user") String user);

}
