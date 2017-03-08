package com.vedenyapin.alex.githubrepositories.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedenyapin.alex.githubrepositories.R;
import com.vedenyapin.alex.githubrepositories.components.DaggerMainComponent;
import com.vedenyapin.alex.githubrepositories.modules.MainPresenterModule;
import com.vedenyapin.alex.githubrepositories.presenter.MainPresenterImpl;

import javax.inject.Inject;

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

    @Inject
    MainPresenterImpl mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainComponent.builder()
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainPresenter.unSubscribe();
    }

    @OnClick(R.id.btn_login)
    public void login() {
        boolean areFieldsCorrect = checkFields();
        if (areFieldsCorrect) {
            mMainPresenter.onLoginClicked(etUserName.getText().toString(), etUserPass.getText().toString());
        }
    }

    private boolean checkFields() {
        boolean result = true;
        if (TextUtils.isEmpty(etUserName.getText().toString())) {
            Toast.makeText(this, getString(R.string.enter_user_name), Toast.LENGTH_SHORT).show();
            result = false;
        }

        if (TextUtils.isEmpty(etUserPass.getText().toString())) {
            Toast.makeText(this, getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            result = false;
        }

        return result;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRepoList() {
        Intent intent = new Intent(this, RepoListActivity.class);
        intent.putExtra(EXTRA_USER_NAME, etUserName.getText().toString());
        startActivity(intent);
    }
}
