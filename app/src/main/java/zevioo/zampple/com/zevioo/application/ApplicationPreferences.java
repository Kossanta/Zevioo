package zevioo.zampple.com.zevioo.application;

import android.content.Context;
import android.content.SharedPreferences;

import innovativeapplications.awarenesslibrary.ConfigurationValues;

/**
 * Created by kgiannoulis on 26/4/2016
 */
public class ApplicationPreferences {

    public static final String DOWNLOAD_STEP_INDEX_PREF_KEY = "downloadStepIndex";
    public static final String DOWNLOAD_QUEUE_PREF_KEY = "downloadQueue";
    public static final String CURRENT_VERSION_CODE = "currentVersionCode";
    public static final String MAP_RESOURCES_UPDATE_NEEDED = "mapResourcesUpdateNeeded";

    /**
     * personal preferences keys
     */
    public static final String ContractID = "ContractID";
    public static final String ContractPrice = "ContractPrice";
    public static final String CID = "CID";
    public static final String VID = "VID";
    public static final String FNM = "FNM";
    public static final String LNM = "LNM";
    public static final String ORI = "ORI";
    public static final String OCC = "OCC";
    public static final String PHN = "PHN";
    public static final String EML = "EML";
    public static final String IMEI = "IMEI";
    public static final String LASTTIMESTAMP = "lastTimeStamp";
    public static final String MAC = "MAC";
    public static final String MID = "MID";
    public static final String LOGGEDIN = "logged";
    public static final String SYNC_PUSH = "sync_push";
    public static final String COUNTRY_CODE = "code_country";


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

    public int getIntPreference(String key) {
        return prefs.getInt(key, 0);
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

    public double getLoudThreshold(){
        return Double.parseDouble(applicationPrefs.getString(NOISE, "0.0"));
    }

    public void updateLoudThreshold(double value){
        String result = String.valueOf(value);
        saveStringPreference(APPLICATION_PREFS,NOISE,result);
    }

    public int getNotificationsTotal(){
        return applicationPrefs.getInt(NOTIFICATIONS, 0);
    }

    public int getVersion() {
        return prefs.getInt(ConfigurationValues.OS, ConfigurationValues.VERSION);
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

    public void firstTime(){
        if (prefs.getBoolean("firstTime",true)){
            saveBooleanPreference(PREFS_NAME,"firstTime",false);
        } else {
            if (!prefs.getBoolean("secondTime",false)){
                saveBooleanPreference(PREFS_NAME,"secondTime",true);
            }
        }
    }

    public boolean isSecondTime(){
        return prefs.getBoolean("secondTime",false);
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

    public boolean getBooleanPreferenceOptimize(String name, String key) {
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

    public boolean getBooleanPreference(String key) {
        return prefs.getBoolean(key, false);
    }

    public String getStringPreference(String key) {
        return prefs.getString(key, "");
    }


    public void saveDownloadStepPreference(int downloadStepIndex) {
        prefsEditor.putInt(DOWNLOAD_STEP_INDEX_PREF_KEY, downloadStepIndex);
        prefsEditor.commit();
    }

    public void setCurrentVersionCode(int versionCode) {
        prefsEditor.putInt(CURRENT_VERSION_CODE, versionCode);
        prefsEditor.commit();
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

    public void saveBooleanPreference(String key, boolean value) {
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }


}
