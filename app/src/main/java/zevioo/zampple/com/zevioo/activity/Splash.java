package zevioo.zampple.com.zevioo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.activity.gdpr.GDPRMain;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkValues();
            }
        }, 500);
    }


    private void checkValues() {
        ApplicationPreferences preferences = ((ApplicationClass) getApplicationContext()).getAppPrefs();
        if (preferences.getStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.LOGGEDIN).equalsIgnoreCase("1")) {
            if (preferences.isValidated()) {
                if (preferences.isGDPRConsented()) {
                    // logged in && validated && gdpr consented
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    // not gdpr consented
                    startActivity(new Intent(this, GDPRMain.class));
                }
            } else {
                // not validated
                startActivity(new Intent(this, ValidateActivity.class));
            }
        } else {
            // not logged in
            startActivity(new Intent(this, Start.class));
        }
        finish();
    }
}
