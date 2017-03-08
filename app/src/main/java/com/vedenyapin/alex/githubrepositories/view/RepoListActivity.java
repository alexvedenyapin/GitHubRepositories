package com.vedenyapin.alex.githubrepositories.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.vedenyapin.alex.githubrepositories.R;
import com.vedenyapin.alex.githubrepositories.components.DaggerRepoListComponent;
import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.modules.RepoListPresenterModule;
import com.vedenyapin.alex.githubrepositories.presenter.RepoListPresenterImpl;
import com.vedenyapin.alex.githubrepositories.view.adapters.RepoAdapter;

import java.util.List;

import javax.inject.Inject;

public class RepoListActivity extends AppCompatActivity implements RepoListView {
    @Inject
    RepoListPresenterImpl mRepoListPresenter;
    private RecyclerView mRecyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        String mUserName = getIntent().getStringExtra(MainActivity.EXTRA_USER_NAME);
        progressDialog = new ProgressDialog(this);

        prepareList();
        DaggerRepoListComponent.builder()
                .repoListPresenterModule(new RepoListPresenterModule(this, mUserName))
                .build()
                .inject(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRepoListPresenter.unSubscribe();
    }

    private void prepareList() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        showHideReposList(false);
        showProgress();
    }

    @Override
    public void showError(String error) {
        hideProgress();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRepos(List<Repo> repos) {
        RecyclerView.Adapter mAdapter = new RepoAdapter(repos);
        mRecyclerView.setAdapter(mAdapter);
        hideProgress();
        showHideReposList(true);
    }

    private void showHideReposList(boolean isShown) {
        mRecyclerView.setVisibility(isShown ? View.VISIBLE : View.GONE);
    }

    private void showProgress() {
        progressDialog.show();
    }

    private void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.hide();
    }
}