package zevioo.zampple.com.zevioo.presenter;

import android.support.design.widget.Snackbar;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;
import zevioo.zampple.com.zevioo.fragment.SuggestionsFragment;
import zevioo.zampple.com.zevioo.tools.InternetStatus;
import zevioo.zampple.com.zevioo.ws.GetSuggestions;
import zevioo.zampple.com.zevioo.ws.Register;
import zevioo.zampple.com.zevioo.ws.WSInformer;
import zevioo.zampple.com.zevioo.ws.WSTool;

public class SuggestionsFragmentPresenter implements WSInformer {

    SuggestionsFragment mFragment;
    GetSuggestions suggestionsWs;
    Snackbar messenger;
    WSTool wsTool;
    ApplicationPreferences preferences;

    public SuggestionsFragmentPresenter(SuggestionsFragment fragment) {
        this.mFragment = fragment;
        this.wsTool = new WSTool(mFragment.getActivity());
        preferences = ((ApplicationClass) mFragment.getActivity().getApplicationContext()).getAppPrefs();
    }

    public void refresh(){
        this.suggestionsWs = new GetSuggestions(this,preferences.getStringPreference(ApplicationPreferences.PREFS_NAME,ApplicationPreferences.CID));
        this.suggestionsWs.execute();
    }

    @Override
    public void onStart(int ws) {
        // TODO SHOW LOADER?
    }

    @Override
    public void onEnd(int ws) {
        mFragment.done();
    }

    @Override
    public void onSuccess(int ws, JSONObject response) throws JSONException {
        wsTool.parseSuggestions(response);
    }

    @Override
    public void onError(int ws, JSONObject response) throws JSONException {

    }

    @Override
    public void onTimeout() {

    }


//    @Override
//    public void onStart(int ws) {
//        messenger = mActivity.pleaseWait(ws);
//    }
//
//    @Override
//    public void onEnd(int ws) {
//    }
//
//    @Override
//    public void onSuccess(int ws, JSONObject response) throws JSONException {
//        try {
//            wsTool.parseRegisterUpdateResponse(response);
//            userProfile.setCid(preferences.getStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.CID));
//            new Executor(mActivity.getApplicationContext(), new Executor.Result() {
//                @Override
//                @SuppressWarnings("unchecked")
//                public void onResultList(List listResult) {
//                }
//
//                @Override
//                public void onResultItem(Object item) {
//                }
//
//                @Override
//                public void insertedOk(long insertedId) {
//                    mActivity.dismissWait(messenger);
//                    mActivity.success();
//                }
//
//                @Override
//                public void actionOk() {
//                }
//            }).addProfile(userProfile);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

//    @Override
//    public void onError(int ws, JSONObject response) throws JSONException {
//        mActivity.dismissWait(messenger);
//        mActivity.somethingWrong(response.getString("MSG"));
//    }
//
//    @Override
//    public void onTimeout() {
//        mActivity.somethingWrong(mActivity.getString(R.string.low_connectivity));
//    }
}
