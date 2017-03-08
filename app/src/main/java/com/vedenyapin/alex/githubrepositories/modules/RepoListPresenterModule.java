package com.vedenyapin.alex.githubrepositories.modules;

import com.vedenyapin.alex.githubrepositories.view.RepoListView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex Vedenyapin on 08.03.2017
 */

@Module
public class RepoListPresenterModule {
    private RepoListView mView;
    private String mUserName;

    public RepoListPresenterModule(RepoListView view, String userName) {
        mView = view;
        mUserName = userName;
    }

    @Provides
    public RepoListView provideRepoListView() {
        return mView;
    }

    @Provides
    public String provideUserName() {
        return mUserName;
    }
}