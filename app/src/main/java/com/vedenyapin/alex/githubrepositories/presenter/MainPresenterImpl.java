package com.vedenyapin.alex.githubrepositories.presenter;

import com.vedenyapin.alex.githubrepositories.model.Model;
import com.vedenyapin.alex.githubrepositories.model.ModelImpl;
import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.view.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;
    private Subscription mSubscription = Subscriptions.empty();

    @Inject
    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
    }

    @Override
    public void onLoginClicked(String userName, String password) {
        /* Unfortunately, I haven't found the working solution of logging in to GitHub via UserName/Password.
        Tried to use the default way of "password login" from OAuth 2.0 specification, but unsuccessfully.
        As GitHub mRepositories are available without any special credentials I omitted this step. */

        /*mSubscription = mModel.authorize(userName, password).subscribe(new Observer<LoginResponse>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                mMainView.showError(e.getLocalizedMessage());
            }

            @Override
            public void onNext(LoginResponse loginResponse) {
                openRepoListActivity();
            }
        });*/

        openRepoListActivity();
    }

    private void openRepoListActivity() {
        mMainView.showRepoList();
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }
}
