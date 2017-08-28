package zevioo.zampple.com.zevioo.application;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kgiannoulis on 10/8/2017
 */
public class ApplicationPreferences {
    /**
     * personal preferences keys
     */
    public static final String CID = "CID";
    public static final String LOGGEDIN = "logged";
    public static final String VALIDATED = "validated";
    public static final String GDPR = "gdpr";
    public static final String A1 = "a1";
    public static final String A2 = "a2";
    public static final String A3 = "a3";
    public static final String A4 = "a4";
    public static final String A5 = "a5";

    public static final String B1 = "b1";
    public static final String B2 = "b2";
    public static final String B3 = "b3";
    public static final String B4 = "b4";

    public static final String C1 = "c1";
    public static final String C2 = "c2";


    /**
     * application preferences keys
     */
    public static final String OSV = "OSV";
    public static final String APV = "APV";
    public static final String TOKEN = "TOKEN";
    public static final String MOBILE_DATA = "MOBILE_DATA";
    public static final String MOBILE_DATA_PROCESS = "MOBILE_DATA_PROCESS";
    public static final String PROFILE_IMG = "profileImage";
    public static final String CAR_IMG = "carImg";
    public static final String NOTIFICATIONS = "notifications";
    public static final String SHOW_GUIDE = "guide";
    public static final String SHOW_OPTIMIZER = "optimizer";

    /**
     * application preferences keys
     */
    public static final String NM = "NM";
    public static final String IT = "IT";
    public static final String MDR = "MDR";
    public static final String FTS = "FTS";
    public static final String NOISE = "noise";

    /**
     * preference name
     */
    public static final String PREFS_NAME = "demoAppPrefs";
    public static final String PERSONAL_PREFS = "personalPrefs";
    public static final String APPLICATION_PREFS = "applicationPrefs";
    public static final String CONTRACT_PREFS = "contractPrefs";

    /**
     * used for modifying values in a SharedPreferences prefs
     */
    private SharedPreferences.Editor prefsEditor;
    private SharedPreferences.Editor personalPrefsEditor;
    private SharedPreferences.Editor applicationPrefsEditor;
    private SharedPreferences.Editor contractPrefsEditor;

    /**
     * reference to preference
     */
    private SharedPreferences prefs;
    private SharedPreferences personalPrefs;
    private SharedPreferences applicationPrefs;
    private SharedPreferences contractPrefs;

    /**
     * the mContext
     */
    Context mContext;

    public ApplicationPreferences(Context context) {
        this.mContext = context;
        prefs = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();
        personalPrefs = mContext.getSharedPreferences(PERSONAL_PREFS, Context.MODE_PRIVATE);
        personalPrefsEditor = personalPrefs.edit();
        applicationPrefs = mContext.getSharedPreferences(APPLICATION_PREFS, Context.MODE_PRIVATE);
        applicationPrefsEditor = applicationPrefs.edit();
        contractPrefs = mContext.getSharedPreferences(CONTRACT_PREFS, Context.MODE_PRIVATE);
        contractPrefsEditor = contractPrefs.edit();
    }


    public int getIntPreference(String name, String key) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            return prefs.getInt(key, 0);
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            return personalPrefs.getInt(key, 0);
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            return contractPrefs.getInt(key, -1);
        } else {
            return applicationPrefs.getInt(key, 0);
        }
    }

    public String getStringPreference(String name, String key) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            return prefs.getString(key, "");
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            return personalPrefs.getString(key, "");
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            return contractPrefs.getString(key, "");
        } else {
            return applicationPrefs.getString(key, "");
        }
    }

    public long getLongPreference(String name, String key) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            return prefs.getLong(key, -1);
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            return personalPrefs.getLong(key, -1);
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            return contractPrefs.getLong(key, -1);
        } else {
            return applicationPrefs.getLong(key, -1);
        }
    }

    public boolean getBooleanPreference(String name, String key) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            return prefs.getBoolean(key, false);
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            return personalPrefs.getBoolean(key, false);
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            return contractPrefs.getBoolean(key, false);
        } else {
            return applicationPrefs.getBoolean(key, false);
        }
    }

    // dame as getBooleanPreference but default value is true not false
    public boolean getBooleanPreference1(String name, String key) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            return prefs.getBoolean(key, true);
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            return personalPrefs.getBoolean(key, true);
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            return contractPrefs.getBoolean(key, true);
        } else {
            return applicationPrefs.getBoolean(key, true);
        }
    }

    public boolean isValidated() {
        return personalPrefs.getBoolean(VALIDATED, false);
    }


    public void accountIsValidated() {
        personalPrefsEditor.putBoolean(VALIDATED, true);
        personalPrefsEditor.commit();
    }

    public boolean isGDPRConsented() {
        return personalPrefs.getBoolean(GDPR, false);
    }


    public void GDPRConsented() {
        personalPrefsEditor.putBoolean(GDPR, true);
        personalPrefsEditor.commit();
    }

    public boolean isGDPRPreferencesOK() {
        return (getBooleanPreference(PERSONAL_PREFS, A1)
                && getBooleanPreference(PERSONAL_PREFS, A2)
                && getBooleanPreference(PERSONAL_PREFS, A3)
                && getBooleanPreference(PERSONAL_PREFS, A4)
                && getBooleanPreference(PERSONAL_PREFS, A5));
    }

    public boolean isGDPRReviewsOK() {
        return (getBooleanPreference(PERSONAL_PREFS, B1)
                && getBooleanPreference(PERSONAL_PREFS, B2)
                && getBooleanPreference(PERSONAL_PREFS, B3)
                && getBooleanPreference(PERSONAL_PREFS, B4));
    }

    public boolean isGDPRPurchasesOK() {
        return (getBooleanPreference(PERSONAL_PREFS, C1)
                && getBooleanPreference(PERSONAL_PREFS, C2));
    }


    public void saveStringPreference(String name, String key, String value) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            prefsEditor.putString(key, value);
            prefsEditor.commit();
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            personalPrefsEditor.putString(key, value);
            personalPrefsEditor.commit();
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            contractPrefsEditor.putString(key, value);
            contractPrefsEditor.commit();
        } else {
            applicationPrefsEditor.putString(key, value);
            applicationPrefsEditor.commit();
        }
    }

    public void saveLongPreference(String name, String key, long value) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            prefsEditor.putLong(key, value);
            prefsEditor.commit();
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            personalPrefsEditor.putLong(key, value);
            personalPrefsEditor.commit();
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            contractPrefsEditor.putLong(key, value);
            contractPrefsEditor.commit();
        } else {
            applicationPrefsEditor.putLong(key, value);
            applicationPrefsEditor.commit();
        }
    }

    public void saveIntPreference(String name, String key, int value) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            prefsEditor.putInt(key, value);
            prefsEditor.commit();
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            personalPrefsEditor.putInt(key, value);
            personalPrefsEditor.commit();
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            contractPrefsEditor.putInt(key, value);
            contractPrefsEditor.commit();
        } else {
            applicationPrefsEditor.putInt(key, value);
            applicationPrefsEditor.commit();
        }
    }

    public void saveBooleanPreference(String name, String key, boolean value) {
        if (name.equalsIgnoreCase(PREFS_NAME)) {
            prefsEditor.putBoolean(key, value);
            prefsEditor.commit();
        } else if (name.equalsIgnoreCase(PERSONAL_PREFS)) {
            personalPrefsEditor.putBoolean(key, value);
            personalPrefsEditor.commit();
        } else if (name.equalsIgnoreCase(CONTRACT_PREFS)) {
            contractPrefsEditor.putBoolean(key, value);
            contractPrefsEditor.commit();
        } else {
            applicationPrefsEditor.putBoolean(key, value);
            applicationPrefsEditor.commit();
        }
    }


}
