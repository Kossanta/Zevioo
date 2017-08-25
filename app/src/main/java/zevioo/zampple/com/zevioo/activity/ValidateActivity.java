package zevioo.zampple.com.zevioo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.presenter.PresenterCallbacks;
import zevioo.zampple.com.zevioo.presenter.ValidateActivityPresenter;
import zevioo.zampple.com.zevioo.presenter.Validator;

public class ValidateActivity extends AppCompatActivity implements Validator, PresenterCallbacks {

    Toolbar toolbar;
    EditText edt1, edt2, edt3, edt4;
    private int maxFields = 4;
    private int currentFilledFields = 0;
    private boolean proceed;
    CoordinatorLayout parent_layout;
    ValidateActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validate_activity);
        proceed = false;
        parent_layout = (CoordinatorLayout) findViewById(R.id.parent_layout);
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);
        edt4 = (EditText) findViewById(R.id.edt4);
        presenter = new ValidateActivityPresenter(this,edt1,edt2,edt3,edt4);
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_validate, menu);
        MenuItem loginAction = menu.findItem(R.id.action_next);
        loginAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.sendValidation();
                return false;
            }
        });
        if (proceed) {
            menu.findItem(R.id.action_next).setVisible(true);
        } else {
            menu.findItem(R.id.action_next).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (proceed) {
            menu.findItem(R.id.action_next).setVisible(true);
        } else {
            menu.findItem(R.id.action_next).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
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

    public void success() {
        startActivity(new Intent(this, MainActivity.class));
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
                presenter.sendValidation();
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

}