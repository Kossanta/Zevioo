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

public class PersonalizeActivity extends AppCompatActivity {

    TextView mmLater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalize_activity);
        mmLater = (TextView) findViewById(R.id.later);
        mmLater.setClickable(true);
        mmLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalizeActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}