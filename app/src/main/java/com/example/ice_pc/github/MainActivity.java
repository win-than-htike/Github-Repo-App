package com.example.ice_pc.github;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvGithubRepo;
    private RepoAdapter mAdapter;
    private List<GithubRepo> repos;
    private CoordinatorLayout coordinatorLayout;
    private ProgressBar loading;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvNoInternetConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvNoInternetConnection = (TextView)findViewById(R.id.tv_no_internet_state);

        if (NetworkUtils.isNetworkAvaliable()){
            tvNoInternetConnection.setVisibility(View.GONE);
        }else {
            tvNoInternetConnection.setVisibility(View.VISIBLE);
            tvNoInternetConnection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fetchRepos();
                }
            });
        }

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        loading = (ProgressBar)findViewById(R.id.loading);
        loading.setVisibility(View.GONE );

        repos = new ArrayList<>();
        rvGithubRepo = (RecyclerView)findViewById(R.id.rv_repos);
        rvGithubRepo.setHasFixedSize(true);
        rvGithubRepo.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(this);

        fetchRepos();

    }

    private void fetchRepos() {

        tvNoInternetConnection.setVisibility(View.GONE);

        if (repos.isEmpty()){
            loading.setVisibility(View.VISIBLE);
        }

        GithubApi api = GithubClient.getInstance().create(GithubApi.class);
        Call<List<GithubRepo>> call = api.getRepos("winthanhtike147");
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                tvNoInternetConnection.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    Log.i("Repos",response.body().size()+"");
                    loading.setVisibility(View.GONE);
                    repos = response.body();
                    mAdapter = new RepoAdapter(repos);
                    rvGithubRepo.setAdapter(mAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                }else {
                    Log.i("Repos","Connection Error");
                }
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                loading.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                tvNoInternetConnection.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onRefresh() {
        fetchRepos();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_refresh) {
//            fetchRepos();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
