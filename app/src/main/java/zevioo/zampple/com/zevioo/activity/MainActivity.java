package zevioo.zampple.com.zevioo.activity;

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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }
}