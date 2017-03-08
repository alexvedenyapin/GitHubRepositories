package com.vedenyapin.alex.githubrepositories.components;

import com.vedenyapin.alex.githubrepositories.modules.MainPresenterModule;
import com.vedenyapin.alex.githubrepositories.view.MainActivity;

import dagger.Component;

/**
 * Created by Alex Vedenyapin on 08.03.2017
 */

@Component(modules = MainPresenterModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}