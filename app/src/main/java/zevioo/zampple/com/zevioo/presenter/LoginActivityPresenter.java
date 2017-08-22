package zevioo.zampple.com.zevioo.presenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import zevioo.zampple.com.zevioo.activity.LoginActivity;
import zevioo.zampple.com.zevioo.tools.InternetStatus;
import zevioo.zampple.com.zevioo.ws.Login;
import zevioo.zampple.com.zevioo.ws.WSInformer;
import zevioo.zampple.com.zevioo.ws.WSTool;

/**
 * Created by kgiannoulis on 22/8/2017
 */

public class LoginActivityPresenter implements WSInformer {

    LoginActivity mActivity;
    String mEmail, mPass;
    Login loginWs;

    public LoginActivityPresenter(LoginActivity activity) {
        this.mActivity = activity;
        this.mEmail = "";
        this.mPass = "";
    }


    public void sendLogin(String email, String pass) {
        this.mEmail = email;
        this.mPass = pass;
        if (InternetStatus.getInstance(mActivity).isOnline(mActivity)) {
            loginWs = new Login(this, getValuesLogin());
            loginWs.execute();
        } else {
            mActivity.noInternet();
        }

    }

    private Map<String, Object> getValuesLogin() {
        Map<String, Object> list = new HashMap<>();
        list.put("EML", mEmail);
        list.put("PSW", mPass);
        return list;
    }

    @Override
    public void onStart(int ws) {
        mActivity.start();
    }

    @Override
    public void onEnd(int ws) {
        mActivity.end();
    }

    @Override
    public void onSuccess(int ws, JSONObject response) throws JSONException {
        // todo parse login response
        mActivity.success();
    }

    @Override
    public void onError(int ws, JSONObject response) throws JSONException {
        mActivity.error(response.getString("MSG"));
    }

    @Override
    public void onTimeout() {
        mActivity.timeout();
    }
}
