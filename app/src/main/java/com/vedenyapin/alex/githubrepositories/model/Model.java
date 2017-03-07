package com.vedenyapin.alex.githubrepositories.model;

import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.model.responses.LoginResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public interface Model {
    Observable<List<Repo>> getRepoList(String userName);

    Observable<LoginResponse> authorize(String userName, String password);
}
