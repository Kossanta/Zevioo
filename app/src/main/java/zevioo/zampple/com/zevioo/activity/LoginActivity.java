package zevioo.zampple.com.zevioo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.presenter.LoginActivityPresenter;
import zevioo.zampple.com.zevioo.view.EditView;

public class LoginActivity extends AppCompatActivity {

    EditView mEmail;
    EditView mPassword;
    Toolbar toolbar;
    LoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mEmail = (EditView) findViewById(R.id.email);
        mPassword = (EditView) findViewById(R.id.password);
        mPassword.init(getString(R.string.registration_pass),EditView.PASSWORD);
        mEmail.init(getString(R.string.registration_email),EditView.EMAIL);
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
                if (mEmail.isValid() && mPassword.isValid()){
                    presenter.sendLogin(mEmail.getValue(),mPassword.getValue());
                } else {
                    mEmail.validate();
                    mPassword.validate();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }



    public void start(){
        // TODO show loader
    }

    public void end(){
        // TODO hide loader
    }

    public void success(){
        // TODO move to next activity main
    }

    public void error(String message){
        // TODO show error message
    }

    public void timeout(){
        // TODO inform user about timeout
    }

    public void noInternet(){
        // TODO inform user about no internet connection
    }

}