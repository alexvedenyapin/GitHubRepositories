package com.vedenyapin.alex.githubrepositories.model;

import com.vedenyapin.alex.githubrepositories.GitHubApplication;
import com.vedenyapin.alex.githubrepositories.model.data.Repo;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public class ModelImpl implements Model {
    private ApiInterface apiInterface = GitHubApplication.getNetworkService().getAPI();

    @Override
    public Observable<List<Repo>> getRepoList(String name) {
        return apiInterface.getRepositories(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
