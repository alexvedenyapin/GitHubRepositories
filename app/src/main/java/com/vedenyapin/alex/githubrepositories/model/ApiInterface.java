package com.vedenyapin.alex.githubrepositories.model;

import com.vedenyapin.alex.githubrepositories.model.data.Repo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public interface ApiInterface {

    @GET("users/{user}/repos")
    Observable<List<Repo>> getRepositories(@Path("user") String user);
}
