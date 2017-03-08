package com.vedenyapin.alex.githubrepositories.presenter;

import com.vedenyapin.alex.githubrepositories.model.Model;
import com.vedenyapin.alex.githubrepositories.model.ModelImpl;
import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.view.RepoListView;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Alex Vedenyapin on 07.03.2017
 */

public class RepoListPresenterImpl implements RepoListPresenter {
    private RepoListView mRepoListView;
    private Model mModel = new ModelImpl();
    private Subscription mSubscription = Subscriptions.empty();
    private String mUserName;

    @Inject
    public RepoListPresenterImpl(RepoListView repoListView, String userName) {
        mRepoListView = repoListView;
        mUserName = userName;
        loadRepos();
    }

    private void loadRepos() {
        mSubscription = mModel.getRepoList(mUserName).subscribe(new Observer<List<Repo>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mRepoListView.showError(e.getLocalizedMessage());
            }

            @Override
            public void onNext(List<Repo> repos) {
                mRepoListView.showRepos(repos);
            }
        });
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}
