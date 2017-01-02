package com.example.ice_pc.github;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ICE-PC on 1/1/2017.
 */
public class GithubClient {

    private static Retrofit objInstance;

    public static synchronized Retrofit getInstance(){
        if (objInstance == null){
            objInstance = new Retrofit.Builder()
                    .baseUrl(Constants.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return objInstance;
    }

}
