package zevioo.zampple.com.zevioo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkValues();
    }


    private void checkValues(){
        ApplicationPreferences preferences = ((ApplicationClass) getApplicationContext()).getAppPrefs();
        if (preferences.getStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.LOGGEDIN).equalsIgnoreCase("1")){
            // logged in
        } else {
            // not logged in
            startActivity(new Intent(this,Start.class));
            finish();
        }
    }
}
