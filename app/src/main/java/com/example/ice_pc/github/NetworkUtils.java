package com.example.ice_pc.github;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ICE-PC on 1/2/2017.
 */
public class NetworkUtils {

    public static boolean isNetworkAvaliable(){
        ConnectivityManager cm =
                (ConnectivityManager)GithubRepoApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
