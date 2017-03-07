package com.vedenyapin.alex.githubrepositories.presenter;

import com.vedenyapin.alex.githubrepositories.R;
import com.vedenyapin.alex.githubrepositories.model.Model;
import com.vedenyapin.alex.githubrepositories.model.ModelImpl;
import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.view.RepoListView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Alex Vedenyapin on 07.03.2017
 */

public class RepoListPresenterImpl implements RepoListPresenter {
    private RepoListView mRrepoListView;
    private Model mModel = new ModelImpl();
    private Subscription mSubscription = Subscriptions.empty();
    private String mUserName;

    public RepoListPresenterImpl(RepoListView repoListView, String userName) {
        mRrepoListView = repoListView;
        mUserName = userName;
        loadRepos();
    }

    private void loadRepos() {
        mSubscription = mModel.getRepoList(mUserName).subscribe(new Observer<List<Repo>>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                //TODO
                mRrepoListView.showError(e.getLocalizedMessage());
            }

            @Override
            public void onNext(List<Repo> repos) {
                mRrepoListView.showRepos(repos);
            }
        });
    }
}
