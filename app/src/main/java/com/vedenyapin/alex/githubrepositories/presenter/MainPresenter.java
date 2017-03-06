package com.vedenyapin.alex.githubrepositories.presenter;

import com.vedenyapin.alex.githubrepositories.model.Model;
import com.vedenyapin.alex.githubrepositories.model.ModelImpl;
import com.vedenyapin.alex.githubrepositories.model.data.Repo;
import com.vedenyapin.alex.githubrepositories.view.View;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Alex Vedenyapin on 06.03.2017
 */

public class MainPresenter implements Presenter {

    private View view;
    private Model model = new ModelImpl();
    private Subscription subscription = Subscriptions.empty();
    List<Repo> repositories = new ArrayList<>();

    public MainPresenter(View view) {
        this.view = view;
    }

    @Override
    public void onLoginClicked() {


    }
}
