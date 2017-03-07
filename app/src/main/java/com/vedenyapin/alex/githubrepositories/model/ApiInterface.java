package com.vedenyapin.alex.githubrepositories.model;

import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.model.responses.LoginResponse;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public interface ApiInterface {

    @GET("users/{user}/repos")
    Observable<List<Repo>> getRepositories(@Path("user") String user);

    @FormUrlEncoded
    @POST("login/oauth/access_token")
    Observable<LoginResponse> authorize(@Field("grant_type") String grantType,
                                        @Field("client_id") String clientId,
                                        @Field("client_secret") String clientSecret,
                                        @Field("username") String userName,
                                        @Field("password") String password);
}
