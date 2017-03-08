package com.vedenyapin.alex.githubrepositories.components;

import com.vedenyapin.alex.githubrepositories.modules.RepoListPresenterModule;
import com.vedenyapin.alex.githubrepositories.view.RepoListActivity;

import dagger.Component;

/**
 * Created by Alex Vedenyapin on 08.03.2017
 */

@Component(modules = RepoListPresenterModule.class)
public interface RepoListComponent {
    void inject(RepoListActivity activity);
}