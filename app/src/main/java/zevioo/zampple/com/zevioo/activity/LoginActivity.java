package zevioo.zampple.com.zevioo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.presenter.LoginActivityPresenter;
import zevioo.zampple.com.zevioo.presenter.PresenterCallbacks;
import zevioo.zampple.com.zevioo.presenter.Validator;
import zevioo.zampple.com.zevioo.view.EditView;

public class LoginActivity extends AppCompatActivity implements Validator, PresenterCallbacks {

    EditView mEmail;
    EditView mPassword;
    Toolbar toolbar;
    LoginActivityPresenter presenter;
    private int maxFields = 2;
    private int currentFilledFields = 0;
    private boolean proceed;
    CoordinatorLayout parent_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        proceed = false;
        parent_layout = (CoordinatorLayout) findViewById(R.id.parent_layout);
        mEmail = (EditView) findViewById(R.id.email);
        mPassword = (EditView) findViewById(R.id.password);
        mPassword.init(getString(R.string.registration_pass), EditView.PASSWORD, this, this);
        mEmail.init(getString(R.string.registration_email), EditView.EMAIL, this, this);
        presenter = new LoginActivityPresenter(this);
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        MenuItem loginAction = menu.findItem(R.id.action_login);
        loginAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mEmail.isValid() && mPassword.isValid()) {
                    presenter.sendLogin(mEmail.getValue(), mPassword.getValue());
                } else {
                    mEmail.validate();
                    mPassword.validate();
                }
                return false;
            }
        });
        if (proceed) {
            menu.findItem(R.id.action_login).setVisible(true);
        } else {
            menu.findItem(R.id.action_login).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (proceed) {
            menu.findItem(R.id.action_login).setVisible(true);
        } else {
            menu.findItem(R.id.action_login).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void success() {
        // todo login -> main (no validation or gdpr consent only in registration use case)
//        if (ApplicationClass.getInstance().getAppPrefs().isValidated()){
        startActivity(new Intent(this, MainActivity.class));
//        } else {
//            startActivity(new Intent(this, ValidateActivity.class));
//        }
    }

    @Override
    public void noInternet() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ApplicationClass.inform(parent_layout, getString(R.string.snack_no_internet), Color.YELLOW);
            }
        });
    }

    @Override
    public void somethingWrong(String msg) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmail.isValid() && mPassword.isValid()) {
                    presenter.sendLogin(mEmail.getValue(), mPassword.getValue());
                } else {
                    mEmail.validate();
                    mPassword.validate();
                }
            }
        };
        ApplicationClass.inform(parent_layout, clickListener,
                msg
                , getString(R.string.snack_action), Color.RED, Color.WHITE);
    }

    @Override
    public Snackbar pleaseWait(int id) {
        return ApplicationClass.inform(parent_layout, Color.WHITE, id);
    }

    @Override
    public void dismissWait(Snackbar bar) {
        bar.dismiss();
    }

    @Override
    public void invalid() {
        currentFilledFields--;
        proceed = false;
        invalidateOptionsMenu();
    }

    @Override
    public void valid() {
        currentFilledFields++;
        if (currentFilledFields == maxFields) {
            proceed = true;
            invalidateOptionsMenu();
        }
    }
}