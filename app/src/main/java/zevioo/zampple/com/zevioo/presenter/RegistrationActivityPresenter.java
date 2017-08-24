package zevioo.zampple.com.zevioo.presenter;

import android.support.design.widget.Snackbar;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.activity.LoginActivity;
import zevioo.zampple.com.zevioo.activity.RegistrationActivity;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;
import zevioo.zampple.com.zevioo.tools.InternetStatus;
import zevioo.zampple.com.zevioo.tools.LocaleHelper;
import zevioo.zampple.com.zevioo.ws.Login;
import zevioo.zampple.com.zevioo.ws.Register;
import zevioo.zampple.com.zevioo.ws.WSInformer;
import zevioo.zampple.com.zevioo.ws.WSTool;
import zevioo.zampple.com.zevioo.κουτί.Executor;
import zevioo.zampple.com.zevioo.κουτί.entity.Profile;

/**
 * Created by kgiannoulis on 22/8/2017
 */

public class RegistrationActivityPresenter implements WSInformer {

    RegistrationActivity mActivity;
    String mNick, mDesc,mGender,mCountry,mLanguage,mEmail,mPass;
    Date mDob;
    Register registerWs;
    Snackbar messenger;
    WSTool wsTool;
    Profile userProfile;
    ApplicationPreferences preferences;

    public RegistrationActivityPresenter(RegistrationActivity activity) {
        this.mActivity = activity;
        this.mEmail = "";
        this.mPass = "";
        this.wsTool = new WSTool(mActivity);
        preferences = ((ApplicationClass) mActivity.getApplicationContext()).getAppPrefs();
    }


    public void sendRegistration(String nickname, String description, String gender, Date dob, String country, String lanugage, String email, String pass) {
        this.mEmail = email;
        this.mPass = pass;
        this.mNick = nickname;
        this.mDesc = description;
        this.mGender = gender;
        this.mDob = dob;
        this.mCountry = country;
        this.mLanguage = lanugage;
        if (InternetStatus.getInstance(mActivity).isOnline(mActivity)) {
            registerWs = new Register(this,generateProfile());
            registerWs.execute();
        } else {
            mActivity.noInternet();
        }

    }

    private Profile generateProfile() {
        userProfile =  new Profile(mNick, mDesc, "", mGender, mDob, mCountry, mLanguage, mEmail, mPass);
        userProfile.setCid("666");
        new Executor(mActivity.getApplicationContext(), new Executor.Result() {
            @Override
            @SuppressWarnings("unchecked")
            public void onResultList(List listResult) {
            }

            @Override
            public void onResultItem(Object item) {
//                mActivity.dismissWait(messenger);
                mActivity.success();
            }

            @Override
            public void insertedOk(long insertedId) {

            }
            @Override
            public void actionOk() {
            }
        }).addProfile(userProfile);
        return userProfile;
    }

    @Override
    public void onStart(int ws) {
        messenger = mActivity.pleaseWait(ws);
    }

    @Override
    public void onEnd(int ws) {
    }

    @Override
    public void onSuccess(int ws, JSONObject response) throws JSONException {
        try {
            wsTool.parseRegisterUpdateResponse(response);
            userProfile.setCid(preferences.getStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.CID));
            new Executor(mActivity.getApplicationContext(), new Executor.Result() {
                @Override
                @SuppressWarnings("unchecked")
                public void onResultList(List listResult) {
                }

                @Override
                public void onResultItem(Object item) {
                    mActivity.dismissWait(messenger);
                    mActivity.success();
                }

                @Override
                public void insertedOk(long insertedId) {

                }
                @Override
                public void actionOk() {
                }
            }).addProfile(userProfile);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(int ws, JSONObject response) throws JSONException {
        mActivity.dismissWait(messenger);
        mActivity.somethingWrong(response.getString("MSG"));
    }

    @Override
    public void onTimeout() {
        mActivity.somethingWrong(mActivity.getString(R.string.low_connectivity));
    }
}
