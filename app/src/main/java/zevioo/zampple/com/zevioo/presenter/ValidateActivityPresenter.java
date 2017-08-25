package zevioo.zampple.com.zevioo.presenter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.activity.ValidateActivity;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;
import zevioo.zampple.com.zevioo.tools.InternetStatus;
import zevioo.zampple.com.zevioo.ws.CheckVFCode;
import zevioo.zampple.com.zevioo.ws.WSInformer;
import zevioo.zampple.com.zevioo.ws.WSTool;

/**
 * Created by kgiannoulis on 22/8/2017
 */

public class ValidateActivityPresenter implements WSInformer, View.OnFocusChangeListener {

    ValidateActivity mActivity;
    EditText edt1, edt2, edt3, edt4;
    Snackbar messenger;
    WSTool wsTool;
    CheckVFCode checkVFCode;
    ApplicationPreferences preferences;
    boolean valid1, valid2, valid3, valid4;

    public ValidateActivityPresenter(ValidateActivity activity, EditText e1, EditText e2, EditText e3, EditText e4) {
        this.mActivity = activity;
        this.edt1 = e1;
        this.edt2 = e2;
        this.edt3 = e3;
        this.edt4 = e4;
        this.wsTool = new WSTool(mActivity);
        preferences = ((ApplicationClass) mActivity.getApplicationContext()).getAppPrefs();
        this.valid1 = false;
        this.valid2 = false;
        this.valid3 = false;
        this.valid4 = false;
        edt1.setOnFocusChangeListener(this);
        edt2.setOnFocusChangeListener(this);
        edt3.setOnFocusChangeListener(this);
        edt4.setOnFocusChangeListener(this);
        edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt1.clearFocus();
                    edt2.requestFocus();
                }
            }
        });
        edt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt2.clearFocus();
                    edt3.requestFocus();
                }
            }
        });
        edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt3.clearFocus();
                    edt4.requestFocus();
                }
            }
        });
        edt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    edt4.clearFocus();
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edt4.getWindowToken(), 0);
                }
            }
        });
    }


    public void sendValidation() {
        if (InternetStatus.getInstance(mActivity).isOnline(mActivity)) {
            closeAllKeyboards();
            checkVFCode = new CheckVFCode(this, edt1.getText().toString()
                    + edt2.getText().toString()
                    + edt3.getText().toString()
                    + edt4.getText().toString(), mActivity);
            checkVFCode.execute();
        } else {
            mActivity.noInternet();
        }

    }

    private void closeAllKeyboards() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edt1.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(edt2.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(edt3.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(edt4.getWindowToken(), 0);
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
        preferences.accountIsValidated();
        mActivity.dismissWait(messenger);
        mActivity.success();
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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edt1:
                if (!hasFocus) {
                    if (!edt1.getText().toString().equalsIgnoreCase("")) {
                        if (!valid1) {
                            valid1 = true;
                            mActivity.valid();
                        }
                    } else {
                        if (valid1) {
                            valid1 = false;
                            mActivity.invalid();
                        }
                    }
                }
                break;
            case R.id.edt2:
                if (!hasFocus) {
                    if (!edt2.getText().toString().equalsIgnoreCase("")) {
                        if (!valid2) {
                            valid2 = true;
                            mActivity.valid();
                        }
                    } else {
                        if (valid2) {
                            valid2 = false;
                            mActivity.invalid();
                        }
                    }
                }
                break;
            case R.id.edt3:
                if (!hasFocus) {
                    if (!edt3.getText().toString().equalsIgnoreCase("")) {
                        if (!valid3) {
                            valid3 = true;
                            mActivity.valid();
                        }
                    } else {
                        if (valid3) {
                            valid3 = false;
                            mActivity.invalid();
                        }
                    }
                }
                break;
            case R.id.edt4:
                if (!hasFocus) {
                    if (!edt4.getText().toString().equalsIgnoreCase("")) {
                        if (!valid4) {
                            valid4 = true;
                            mActivity.valid();
                        }
                    } else {
                        if (valid4) {
                            valid4 = false;
                            mActivity.invalid();
                        }
                    }
                }
                break;
        }
    }
}
