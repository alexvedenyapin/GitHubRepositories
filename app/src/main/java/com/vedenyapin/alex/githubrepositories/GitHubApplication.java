package com.vedenyapin.alex.githubrepositories;

import android.app.Application;

import com.vedenyapin.alex.githubrepositories.model.NetworkService;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public class GitHubApplication extends Application {

    private static NetworkService networkService;
    @Override
    public void onCreate() {
        super.onCreate();
        networkService = new NetworkService();
    }

    public static NetworkService getNetworkService(){
        return networkService;
    }
}