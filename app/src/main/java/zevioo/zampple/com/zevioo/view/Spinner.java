package zevioo.zampple.com.zevioo.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.activity.RegistrationActivity;
import zevioo.zampple.com.zevioo.adapter.CustomSpinnerAdapter;
import zevioo.zampple.com.zevioo.presenter.Validator;
import zevioo.zampple.com.zevioo.ws.GetCountries;
import zevioo.zampple.com.zevioo.ws.GetLanguages;
import zevioo.zampple.com.zevioo.ws.WSInformer;
import zevioo.zampple.com.zevioo.ws.WSTool;
import zevioo.zampple.com.zevioo.κουτί.entity.SimpleItem;


/**
 * Created by kgiannoulis on 20/8/2017
 */
public class Spinner extends RelativeLayout implements View.OnClickListener, WSInformer {


    private Context mContext;
    private ImageView error, arrow;
    private RelativeLayout layout;
    private CustomSpinner data;
    private TextView  error_msg, title;
    public static final int COUNTRIES = 1;
    public static final int LANGUAGES = 2;
    private int mType;
    private String mHint;
    private String mErrorMessage;
    private boolean errorIsShown;
    private boolean isValid;
    private ProgressBar progress;
    private WSTool wsTool;
    private SimpleItem mSelectedItem;
    private RegistrationActivity mActivity;
    private ArrayList<SimpleItem> resultList;


    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mHint = "";
        this.errorIsShown = false;
        this.isValid = false;
        this.resultList = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = inflater.inflate(R.layout.spinner, this);
        error = (ImageView) mainView.findViewById(R.id.error);
        arrow = (ImageView) mainView.findViewById(R.id.arrow);
        title = (TextView) mainView.findViewById(R.id.title);
        data = (CustomSpinner) mainView.findViewById(R.id.data);
        progress = (ProgressBar) mainView.findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        error_msg = (TextView) mainView.findViewById(R.id.error_msg);
        layout = (RelativeLayout) mainView.findViewById(R.id.layout);
        error.setClickable(true);
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
        data.setVisibility(View.GONE);
        arrow.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        layout.setOnClickListener(this);
        data.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onSpinnerOpened(android.widget.Spinner spinner) {
                arrow.setImageResource(R.drawable.spinner_opened);
            }

            @Override
            public void onSpinnerClosed(android.widget.Spinner spinner) {
                arrow.setImageResource(R.drawable.spinner_closed);
            }
        });
        data.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent != null && parent.getChildAt(0) != null) {
                    ((TextView) parent.getChildAt(0).findViewById(R.id.text)).setTextColor(Color.parseColor("#000000"));
                }
                mSelectedItem = resultList.get(position);
                isValid = true;
                focusOff();
                mActivity.valid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               isValid = false;
            }
        });
    }

    public boolean isValid(){
        return isValid;
    }

    public void init(String hint, String errorMessage, int type, RegistrationActivity activity) {
        this.mHint = hint;
        this.mErrorMessage = errorMessage;
        this.mType = type;
        this.mActivity = activity;
        wsTool = new WSTool(mContext);
        error_msg.setText(mErrorMessage);
        error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideError();
                if (mType == COUNTRIES){
                    GetCountries countries = new GetCountries(Spinner.this,mContext);
                    countries.execute();
                } else {
                    GetLanguages languages = new GetLanguages(Spinner.this,mContext);
                    languages.execute();
                }
            }
        });
        title.setText(mHint);
        title.setTextColor(ContextCompat.getColor(mContext, R.color.lighter_gray));
        if (mType == COUNTRIES){
            GetCountries countries = new GetCountries(this,mContext);
            countries.execute();
        } else {
            GetLanguages languages = new GetLanguages(this,mContext);
            languages.execute();
        }
    }

    public String getValue() {
        return title.getText().toString();
    }

    public String getValueId() {
        return title.getText().toString();
    }


    private void focusOn(){
        error_msg.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
        data.setVisibility(View.VISIBLE);
        title.setVisibility(View.GONE);
        data.performClick();
    }

    private void focusOff(){
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
        title.setText(mSelectedItem.toString());
        data.setVisibility(View.INVISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setTextColor(ContextCompat.getColor(mContext, R.color.black));
    }

    public void showError() {
        error.setVisibility(View.VISIBLE);
        error_msg.setVisibility(View.VISIBLE);
    }

    public void hideError(){
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        focusOn();
        // todo open spinner
    }

    @Override
    public void onStart(int ws) {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onEnd(int ws) {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(int ws, JSONObject response) throws JSONException {
        resultList = new ArrayList<>();
        if (ws == WSTool.GET_COUNTRIES){
            resultList = wsTool.parseSimpleItem(response, SimpleItem.COUNTRY);
        } else {
            resultList = wsTool.parseSimpleItem(response, SimpleItem.LANGUAGE);
        }
        // TODO fill spinner with results
        final CustomSpinnerAdapter spinnerArrayAdapter = new CustomSpinnerAdapter(mContext, R.layout.spinner_item, resultList); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_drop_down);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                data.setAdapter(spinnerArrayAdapter);
                data.setSelection(0);
                data.setVisibility(View.VISIBLE);
                arrow.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onError(int ws, JSONObject response) throws JSONException {
        isValid = false;
        error_msg.setText(response.getString("MSG"));
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTimeout() {

    }
}
