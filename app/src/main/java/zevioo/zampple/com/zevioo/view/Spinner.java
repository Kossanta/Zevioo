package zevioo.zampple.com.zevioo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zevioo.zampple.com.zevioo.R;


/**
 * Created by kgiannoulis on 20/8/2017
 */
public class Spinner extends RelativeLayout {


    private Context mContext;
    private ImageView error;
    private RelativeLayout layout;
    private android.support.v7.widget.AppCompatSpinner data;
    private TextView  error_msg, title;
    private String mHint;
    private String mErrorMessage;
    private boolean errorIsShown;
    private boolean isValid;


    public Spinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mHint = "";
        this.errorIsShown = false;
        this.isValid = false;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = inflater.inflate(R.layout.spinner, this);
        error = (ImageView) mainView.findViewById(R.id.error);
        title = (TextView) mainView.findViewById(R.id.title);
        data = (android.support.v7.widget.AppCompatSpinner) mainView.findViewById(R.id.data);

        error_msg = (TextView) mainView.findViewById(R.id.error_msg);
        layout = (RelativeLayout) mainView.findViewById(R.id.layout);
        error.setClickable(true);
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
        data.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
    }

    public boolean isValid(){
        return isValid;
    }

    public void init(String hint, String errorMessage) {
        this.mHint = hint;
        this.mErrorMessage = errorMessage;
        error_msg.setText(mErrorMessage);
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
        title.setText(mHint);
    }

    private void focusOn(){
        error_msg.setVisibility(View.GONE);
        title.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    private void focusOff(){
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
//        title.setText(data.getText().toString());
        title.setVisibility(View.VISIBLE);
        data.setVisibility(View.GONE);
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
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
    }
}
