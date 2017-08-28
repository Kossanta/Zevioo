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

public class GDPRPreferences extends AppCompatActivity {

    Toolbar toolbar;
    ApplicationPreferences preferences;
    RelativeLayout mA1_layout, mA2_layout, mA3_layout, mA4_layout, mA5_layout;
    Switch mA1_toggler, mA2_toggler, mA3_toggler, mA4_toggler, mA5_toggler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gdpr_preferences_activity);
        initToolbar();
        preferences = ((ApplicationClass) getApplicationContext()).getAppPrefs();
        mA1_layout = (RelativeLayout) findViewById(R.id.a1_layout);
        mA2_layout = (RelativeLayout) findViewById(R.id.a2_layout);
        mA3_layout = (RelativeLayout) findViewById(R.id.a3_layout);
        mA4_layout = (RelativeLayout) findViewById(R.id.a4_layout);
        mA5_layout = (RelativeLayout) findViewById(R.id.a5_layout);

        mA1_toggler = (Switch) findViewById(R.id.a1_toggle);
        mA2_toggler = (Switch) findViewById(R.id.a2_toggle);
        mA3_toggler = (Switch) findViewById(R.id.a3_toggle);
        mA4_toggler = (Switch) findViewById(R.id.a4_toggle);
        mA5_toggler = (Switch) findViewById(R.id.a5_toggle);

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
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A1, mA1_toggler.isChecked());
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A2, mA2_toggler.isChecked());
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A3, mA3_toggler.isChecked());
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A4, mA4_toggler.isChecked());
        preferences.saveBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A5, mA5_toggler.isChecked());
    }

    private void instantiateLayout() {
        mA1_layout.setClickable(true);
        mA1_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mA1_toggler.toggle();
            }
        });
        mA2_layout.setClickable(true);
        mA2_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mA2_toggler.toggle();
            }
        });
        mA3_layout.setClickable(true);
        mA3_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mA3_toggler.toggle();
            }
        });
        mA4_layout.setClickable(true);
        mA4_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mA4_toggler.toggle();
            }
        });
        mA5_layout.setClickable(true);
        mA5_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mA5_toggler.toggle();
            }
        });
        mA1_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(1);
                }
            }
        });
        mA2_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(2);
                }
            }
        });
        mA3_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(3);
                }
            }
        });
        mA4_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(4);
                }
            }
        });
        mA5_toggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    showAlert(5);
                }
            }
        });
        mA1_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A1));
        mA2_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A2));
        mA3_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A3));
        mA4_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A4));
        mA5_toggler.setChecked(preferences.getBooleanPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.A5));
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
        toolbar.setTitle(getString(R.string.gdpr_preferences_title));
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
                                mA1_toggler.setChecked(false);
                                break;
                            case 2:
                                mA2_toggler.setChecked(false);
                                break;
                            case 3:
                                mA3_toggler.setChecked(false);
                                break;
                            case 4:
                                mA4_toggler.setChecked(false);
                                break;
                            case 5:
                                mA5_toggler.setChecked(false);
                                break;
                        }
                    }
                })
                .setNegativeButton(getString(R.string.gdpr_dialog_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (selected) {
                            case 1:
                                mA1_toggler.setChecked(true);
                                break;
                            case 2:
                                mA2_toggler.setChecked(true);
                                break;
                            case 3:
                                mA3_toggler.setChecked(true);
                                break;
                            case 4:
                                mA4_toggler.setChecked(true);
                                break;
                            case 5:
                                mA5_toggler.setChecked(true);
                                break;
                        }
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}