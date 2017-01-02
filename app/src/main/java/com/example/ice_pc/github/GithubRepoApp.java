package com.example.ice_pc.github;

import android.app.Application;
import android.content.Context;

/**
 * Created by ICE-PC on 1/1/2017.
 */
public class GithubRepoApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }
}
