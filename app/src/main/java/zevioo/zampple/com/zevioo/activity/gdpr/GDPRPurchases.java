package zevioo.zampple.com.zevioo.activity.gdpr;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;

public class GDPRPurchases extends AppCompatActivity {

    Toolbar toolbar;
    ApplicationPreferences preferences;
    RelativeLayout mC1_layout, mC2_layout;
    Switch mC1_toggler, mC2_toggler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gdpr_purchases_activity);
        initToolbar();
        preferences = ((ApplicationClass) getApplicationContext()).getAppPrefs();
        mC1_layout = (RelativeLayout) findViewById(R.id.c1_layout);
        mC2_layout = (RelativeLayout) findViewById(R.id.c2_layout);

        mC1_toggler = (Switch) findViewById(R.id.c1_toggle);
        mC2_toggler = (Switch) findViewById(R.id.c2_toggle);

        instantiateLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        MenuItem loginAction = menu.findItem(R.id.action_done);
        loginAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                updatePreferences();
                finish();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void updatePreferences() {
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.C1, mC1_toggler.isChecked());
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.C2, mC2_toggler.isChecked());
    }

    private void instantiateLayout() {
        mC1_layout.setClickable(true);
        mC1_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mC1_toggler.toggle();
            }
        });
        mC2_layout.setClickable(true);
        mC2_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mC2_toggler.toggle();
            }
        });
        mC1_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(1);
                }
            }
        });
        mC2_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(2);
                }
            }
        });
        mC1_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.C1));
        mC2_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.C2));
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
        toolbar.setTitle(getString(R.string.gdpr_purchases_title));
    }

    private void showAlert(final int selected) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.gdpr_dialog_title))
                .setMessage(getString(R.string.gdpr_dialog_message))
                .setPositiveButton(getString(R.string.gdpr_dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (selected) {
                            case 1:
                               mC1_toggler.setChecked(false);
                                break;
                            case 2:
                                mC2_toggler.setChecked(false);
                                break;
                        }
                    }
                })
                .setNegativeButton(getString(R.string.gdpr_dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (selected) {
                            case 1:
                                mC1_toggler.setChecked(true);
                                break;
                            case 2:
                                mC2_toggler.setChecked(true);
                                break;
                        }
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}