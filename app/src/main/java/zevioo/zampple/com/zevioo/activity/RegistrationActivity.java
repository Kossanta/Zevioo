package zevioo.zampple.com.zevioo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.view.EditView;

public class RegistrationActivity extends AppCompatActivity {

    EditView mNickName;
    EditView mDescription;
    EditView mPassword;
    EditView mEmail;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        mNickName = (EditView) findViewById(R.id.nickname);
        mDescription = (EditView) findViewById(R.id.description);
        mPassword = (EditView) findViewById(R.id.password);
        mEmail = (EditView) findViewById(R.id.email);
        mNickName.init(getString(R.string.registration_nick),EditView.NUMBER, "Den einai dia8esimo");
        mDescription.init(getString(R.string.registration_desc),EditView.TEXT,"");
        mPassword.init(getString(R.string.registration_pass),EditView.PASSWORD,"");
        mEmail.init(getString(R.string.registration_email),EditView.EMAIL,"Wrong email");
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
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return super.onCreateOptionsMenu(menu);
    }

}