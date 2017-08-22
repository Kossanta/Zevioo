package zevioo.zampple.com.zevioo.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zevioo.zampple.com.zevioo.R;


/**
 * Created by kgiannoulis on 8/8/2016
 */
public class EditView extends RelativeLayout implements View.OnClickListener, View.OnFocusChangeListener {


    private Context mContext;
    private ImageView error;
    private RelativeLayout layout;
    private EditText data;
    private TextView  error_msg, title;
    private int mKeyboardType;
    public static int NICKNAME = 1;
    public static int EMAIL = 2;
    public static int PASSWORD = 3;
    public static int NUMBER = 4;
    public static int TEXT = 5;
    private String mHint;
    private boolean errorIsShown;
    private boolean isValid;


    public EditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mHint = "";
        this.errorIsShown = false;
        this.isValid = false;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = inflater.inflate(R.layout.edit, this);
        error = (ImageView) mainView.findViewById(R.id.error);
        title = (TextView) mainView.findViewById(R.id.title);
        data = (EditText) mainView.findViewById(R.id.data);
        data.setOnFocusChangeListener(this);
        data.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(data.getWindowToken(), 0);
                    data.clearFocus();
                }
                return false;
            }
        });
        error_msg = (TextView) mainView.findViewById(R.id.error_msg);
        layout = (RelativeLayout) mainView.findViewById(R.id.layout);
        error.setClickable(true);
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
        error_msg.setOnClickListener(this);
        layout.setOnClickListener(this);
        data.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
    }

    public String getValue() {
            return data.getText().toString();
    }

    public boolean isValid(){
        return isValid;
    }

    public void init(String hint, int keyboardType) {
        this.mHint = hint;
        this.mKeyboardType = keyboardType;
        error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (errorIsShown){
                    errorIsShown = false;
                    hideError();
                } else {
                    errorIsShown = true;
                    showError();
                }
            }
        });
        inputType();
        data.setHint(mHint);
        title.setText(mHint);
        title.setTextColor(ContextCompat.getColor(mContext, R.color.lighter_gray));
    }


    private void inputType() {
        switch (mKeyboardType) {
            case 1:
                data.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case 2:
                data.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case 3:
                break;
            case 4:
                data.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 5:
                data.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case 6:
                data.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        data.setVisibility(View.VISIBLE);
        data.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInputFromWindow(
                data.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == R.id.data) {
            if (b) {
                focusOn();
            } else {
                focusOff();
                validate();
            }
        }
    }

    private void focusOn(){
        error_msg.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    private void focusOff(){
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
        if (mKeyboardType==EditView.PASSWORD){
            String str = "";
            for (int i=0;i<data.getText().toString().length();i++){
                str = str + "*";
            }
            title.setText(str);
            title.setTextColor(ContextCompat.getColor(mContext, R.color.darker_gray));
        } else {
                title.setText(data.getText().toString());
            title.setTextColor(ContextCompat.getColor(mContext, R.color.darker_gray));
        }
        if (title.getText().toString().equalsIgnoreCase("")){
            title.setText(mHint);
            title.setTextColor(ContextCompat.getColor(mContext, R.color.lighter_gray));
        }
        title.setVisibility(View.VISIBLE);
        data.setVisibility(View.GONE);
    }

    public void validate(){
        if (mKeyboardType == NICKNAME){
            // TODO check via WS the validity of the nickname when return the ws set view as valid or not
        } else if (mKeyboardType == EMAIL) {
            isValid = isEmailValid(data.getText().toString());
            if (!isValid){
                error.setVisibility(View.VISIBLE);
                error_msg.setText("Not valid email");
            }
        } else if (mKeyboardType == PASSWORD) {
            if (data.getText().toString().length()>=8){
                isValid = true;
            } else if (data.getText().toString().equalsIgnoreCase("")) {
                error_msg.setText("Must not be empty");
                isValid = false;
            } else {
                error_msg.setText("Must be at least 8 characters long");
                isValid = false;
            }
            if (!isValid){
                error.setVisibility(View.VISIBLE);
            }
        } else {
            if (data.getText().toString().equalsIgnoreCase("")){
                isValid = false;
            } else {
                isValid = true;
            }
            if (!isValid){
                error_msg.setText("Must not be empty");
                error.setVisibility(View.VISIBLE);
            }
        }

    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        Log.e("", "dispatchKeyEventPreIme(" + event + ")");
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            data.clearFocus();
        }
        return super.dispatchKeyEventPreIme(event);
    }

    public void showError() {
        title.setVisibility(View.GONE);
        data.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
        error_msg.setVisibility(View.VISIBLE);
    }

    public void hideError(){
        title.setVisibility(View.VISIBLE);
        data.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
    }
}
