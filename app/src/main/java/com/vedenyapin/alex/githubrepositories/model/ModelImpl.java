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
    private NetworkService networkService = GitHubApplication.getNetworkService();
    private static final String grantType ="password";
    private static final String clientId ="9d2b4db9af4876c46e31";
    private static final String clientSecret ="24c80febb08809f3e6b269f18f6a21a6305ef972";

    @Override
    public Observable<List<Repo>> getRepoList(String userName) {
        Observable<List<Repo>> reposObservable = (Observable<List<Repo>>)
                networkService.getPreparedObservable(networkService.getAPI().getRepositories(userName), userName, true, true);

        return reposObservable;
    }

    @Override
    public Observable<LoginResponse> authorize(String userName, String password) {
        return networkService.getAPI().authorize(grantType, clientId, clientSecret, userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
