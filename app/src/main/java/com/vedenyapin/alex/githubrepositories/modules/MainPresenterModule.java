package com.vedenyapin.alex.githubrepositories.modules;

import com.vedenyapin.alex.githubrepositories.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex Vedenyapin on 08.03.2017
 */

@Module
public class MainPresenterModule {
    private MainView mView;

    public MainPresenterModule(MainView view) {
        mView= view;
    }

    @Provides
    public MainView provideMainView() {
        return mView;
    }
}