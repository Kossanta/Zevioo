package zevioo.zampple.com.zevioo.activity.gdpr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.activity.MainActivity;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;

public class GDPRMain extends AppCompatActivity {

    Toolbar toolbar;
    TextView mActionOne, mActionTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gdpr_main_activity);
        initToolbar();
        mActionOne = (TextView) findViewById(R.id.action_one);
        mActionTwo = (TextView) findViewById(R.id.action_two);
        mActionOne.setClickable(true);
        mActionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationPreferences preferences = ((ApplicationClass) getApplicationContext()).getAppPrefs();
                preferences.GDPRConsented();
                startActivity(new Intent(GDPRMain.this,PersonalizeActivity.class));
                finish();
            }
        });
        mActionTwo.setClickable(true);
        mActionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GDPRMain.this,GDPRConfiguration.class));
            }
        });
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
}