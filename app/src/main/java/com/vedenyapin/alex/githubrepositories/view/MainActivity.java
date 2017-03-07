package com.vedenyapin.alex.githubrepositories.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedenyapin.alex.githubrepositories.R;
import com.vedenyapin.alex.githubrepositories.presenter.MainMainPresenterImpl;
import com.vedenyapin.alex.githubrepositories.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    public static final String EXTRA_USER_NAME = "EXTRA_USER_NAME";

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_pass)
    EditText etUserPass;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainMainPresenterImpl(this);
    }

    @OnClick(R.id.btn_login)
    public void submit() {
        mMainPresenter.onLoginClicked(etUserName.getText().toString(), etUserPass.getText().toString());
    }

    @Override
    public void showError(int res) {
        Toast.makeText(this, getString(res), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRepoList() {
        Intent intent = new Intent(this, RepoListActivity.class);
        intent.putExtra(EXTRA_USER_NAME, etUserName.getText().toString());
        startActivity(intent);
    }
}