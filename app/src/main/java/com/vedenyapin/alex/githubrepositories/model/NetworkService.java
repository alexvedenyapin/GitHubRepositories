package com.vedenyapin.alex.githubrepositories.model;

import android.util.LruCache;

import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.model.responses.LoginResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public class NetworkService {

    private static final String baseUrl ="https://api.github.com/";
    private ApiInterface apiInterface;
    private LruCache<Class<?>, Observable<?>> observablesCache;

    public NetworkService() {
        this(baseUrl);
    }

    public NetworkService(String baseUrl) {
        OkHttpClient okHttpClient = buildClient();
        observablesCache = new LruCache<>(10);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getAPI(){
        return apiInterface;
    }

    public OkHttpClient buildClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            }
        });

        return builder.build();
    }

    public void clearCache(){
        observablesCache.evictAll();
    }

    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Class<?> clazz, boolean cacheObservable, boolean useCache) {
        Observable<?> preparedObservable = null;

        if (useCache)
            preparedObservable = observablesCache.get(clazz);

        if (preparedObservable != null)
            return preparedObservable;

        preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        if (cacheObservable){
            preparedObservable = preparedObservable.cache();
            observablesCache.put(clazz, preparedObservable);
        }

        return preparedObservable;
    }

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
}
