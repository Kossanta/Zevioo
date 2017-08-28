package zevioo.zampple.com.zevioo.activity.gdpr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zevioo.zampple.com.zevioo.R;

public class GDPRConfiguration extends AppCompatActivity {

    Toolbar toolbar;
    RelativeLayout mPreferencesLayout, mReviewsLayout, mPurchasesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gdpr_configuration_activity);
        initToolbar();
        mPreferencesLayout = (RelativeLayout) findViewById(R.id.preferences_layout);
        mPreferencesLayout.setClickable(true);
        mPreferencesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GDPRConfiguration.this,GDPRPreferences.class));
            }
        });
        mReviewsLayout = (RelativeLayout) findViewById(R.id.reviews_layout);
        mReviewsLayout.setClickable(true);
        mReviewsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GDPRConfiguration.this,GDPRReviews.class));
            }
        });
        mPurchasesLayout = (RelativeLayout) findViewById(R.id.purchases_layout);
        mPurchasesLayout.setClickable(true);
        mPurchasesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GDPRConfiguration.this,GDPRPurchases.class));
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
        getSupportActionBar().setTitle(getString(R.string.gdpr_configuration_title));
    }
}