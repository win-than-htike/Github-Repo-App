package com.example.ice_pc.github;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ICE-PC on 1/1/2017.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {

    private LayoutInflater inflater;
    private List<GithubRepo> repos;

    public RepoAdapter(List<GithubRepo> repos) {
        this.repos = repos;
        inflater = LayoutInflater.from(GithubRepoApp.getContext());
    }

    @Override
    public RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.repo_card,parent,false);
        return new RepoHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RepoHolder holder, int position) {
        holder.bindData(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class RepoHolder extends RecyclerView.ViewHolder {

        private TextView tvRepoName;
        private Button btnRepoLink;

        public RepoHolder(View itemView) {
            super(itemView);

            tvRepoName = (TextView)itemView.findViewById(R.id.tv_repo_name);
            btnRepoLink = (Button)itemView.findViewById(R.id.btn_repo_link);

        }

        public void bindData(final GithubRepo repo){
            tvRepoName.setText(repo.getName());

            btnRepoLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent broswerIntent = new Intent(Intent.ACTION_VIEW);
                    broswerIntent.setData(Uri.parse(repo.getUrl()));
                    broswerIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    GithubRepoApp.getContext().startActivity(broswerIntent);
                }
            });
        }

    }
}
