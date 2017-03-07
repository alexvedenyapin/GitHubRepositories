package com.vedenyapin.alex.githubrepositories.model;

import com.vedenyapin.alex.githubrepositories.GitHubApplication;
import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.model.responses.LoginResponse;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public class ModelImpl implements Model {
    private ApiInterface apiInterface = GitHubApplication.getNetworkService().getAPI();
    private static final String grantType ="password";
    private static final String clientId ="9d2b4db9af4876c46e31";
    private static final String clientSecret ="24c80febb08809f3e6b269f18f6a21a6305ef972";

    @Override
    public Observable<List<Repo>> getRepoList(String name) {
        return apiInterface.getRepositories(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<LoginResponse> authorize(String userName, String password) {
        return apiInterface.authorize(grantType, clientId, clientSecret, userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
