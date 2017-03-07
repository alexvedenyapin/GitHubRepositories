package com.vedenyapin.alex.githubrepositories.view;

import com.vedenyapin.alex.githubrepositories.model.data.Repo;

import java.util.List;

/**
 * Created by Alex Vedenyapin on 07.03.2017
 */

public interface RepoListView {
    void showError(String login_error);

    void showRepos(List<Repo> repos);
}
