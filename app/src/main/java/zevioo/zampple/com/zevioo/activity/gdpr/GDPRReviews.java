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

public class GDPRReviews extends AppCompatActivity {

    Toolbar toolbar;
    ApplicationPreferences preferences;
    RelativeLayout mB1_layout, mB2_layout, mB3_layout, mB4_layout;
    Switch mB1_toggler, mB2_toggler, mB3_toggler, mB4_toggler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gdpr_reviews_activity);
        initToolbar();
        preferences = ((ApplicationClass) getApplicationContext()).getAppPrefs();
        mB1_layout = (RelativeLayout) findViewById(R.id.b1_layout);
        mB2_layout = (RelativeLayout) findViewById(R.id.b2_layout);
        mB3_layout = (RelativeLayout) findViewById(R.id.b3_layout);
        mB4_layout = (RelativeLayout) findViewById(R.id.b4_layout);

        mB1_toggler = (Switch) findViewById(R.id.b1_toggle);
        mB2_toggler = (Switch) findViewById(R.id.b2_toggle);
        mB3_toggler = (Switch) findViewById(R.id.b3_toggle);
        mB4_toggler = (Switch) findViewById(R.id.b4_toggle);

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
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.B1, mB1_toggler.isChecked());
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.B2, mB2_toggler.isChecked());
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.B3, mB3_toggler.isChecked());
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.B4, mB4_toggler.isChecked());
    }

    private void instantiateLayout() {
        mB1_layout.setClickable(true);
        mB1_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mB1_toggler.toggle();
            }
        });
        mB2_layout.setClickable(true);
        mB2_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mB2_toggler.toggle();
            }
        });
        mB3_layout.setClickable(true);
        mB3_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mB3_toggler.toggle();
            }
        });
        mB4_layout.setClickable(true);
        mB4_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mB4_toggler.toggle();
            }
        });
        mB1_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(1);
                }
            }
        });
        mB2_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(2);
                }
            }
        });
        mB3_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(3);
                }
            }
        });
        mB4_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(4);
                }
            }
        });
        mB1_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.B1));
        mB2_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.B2));
        mB3_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.B3));
        mB4_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.B4));
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
                               mB1_toggler.setChecked(false);
                                break;
                            case 2:
                                mB2_toggler.setChecked(false);
                                break;
                            case 3:
                                mB3_toggler.setChecked(false);
                                break;
                            case 4:
                                mB4_toggler.setChecked(false);
                                break;
                        }
                    }
                })
                .setNegativeButton(getString(R.string.gdpr_dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (selected) {
                            case 1:
                                mB1_toggler.setChecked(true);
                                break;
                            case 2:
                                mB2_toggler.setChecked(true);
                                break;
                            case 3:
                                mB3_toggler.setChecked(true);
                                break;
                            case 4:
                                mB4_toggler.setChecked(true);
                                break;
                        }
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}