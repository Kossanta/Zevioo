package zevioo.zampple.com.zevioo.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.presenter.Validator;


/**
 * Created by kgiannoulis on 8/8/2016
 */
public class ButtonTextView extends RelativeLayout implements View.OnClickListener, DatePickerDialog.OnDateSetListener {


    private Context mContext;
    private ImageView error;
    private RelativeLayout layout;
    private TextView error_msg, title;
    private int mType;
    public static final int DOB = 1;
    private String mHint;
    private boolean errorIsShown;
    private boolean isValid;
    private Validator mValidator;


    public ButtonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mHint = "";
        this.errorIsShown = false;
        this.isValid = false;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = inflater.inflate(R.layout.button_text_view, this);
        error = (ImageView) mainView.findViewById(R.id.error);
        title = (TextView) mainView.findViewById(R.id.title);
        error_msg = (TextView) mainView.findViewById(R.id.error_msg);
        layout = (RelativeLayout) mainView.findViewById(R.id.layout);
        error.setClickable(true);
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
        error_msg.setOnClickListener(this);
        layout.setOnClickListener(this);
        title.setVisibility(View.VISIBLE);
    }

    public Date getValue() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(title.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void validate(){
        if (isValid){
            select(title.getText().toString());
        } else {
            cancel();
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public void init(String hint, int type, Validator validator) {
        this.mHint = hint;
        this.mType = type;
        this.mValidator = validator;
        error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (errorIsShown) {
                    errorIsShown = false;
                    hideError();
                } else {
                    errorIsShown = true;
                    showError();
                }
            }
        });
        title.setText(mHint);
        title.setTextColor(ContextCompat.getColor(mContext, R.color.lighter_gray));
    }

    @Override
    public void onClick(View view) {
        switch (mType) {
            case DOB:
                focusOn();
                showDatePicker();
                break;
        }
    }

    private void showDatePicker() {
        // TODO
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                mContext, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setCancelable(true);
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel();
            }
        });
        datePickerDialog.show();
    }

    private void focusOn() {
        error_msg.setVisibility(View.GONE);
        error.setVisibility(View.GONE);
    }

    private void cancel(){
        if (isValid){
            mValidator.invalid();
        }
        isValid = false;
        error_msg.setVisibility(View.GONE);
        title.setText(mHint);
        title.setTextColor(ContextCompat.getColor(mContext, R.color.lighter_gray));
        error.setVisibility(View.VISIBLE);
        error_msg.setText("Must not be empty");
    }

    private void select(String date){
        if (!isValid){
            mValidator.valid();
        }
        isValid = true;
        error.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        title.setText(date);

    }

//    public void validate(){
//        if (mKeyboardType == NICKNAME){
//            // TODO check via WS the validity of the nickname when return the ws set view as valid or not
//            CheckNickName checkNick = new CheckNickName(this,getValues(),mContext);
//            checkNick.execute();
//        } else if (mKeyboardType == EMAIL) {
//            isValid = isEmailValid(data.getText().toString());
//            if (!isValid){
//                error.setVisibility(View.VISIBLE);
//                error_msg.setText("Not valid email");
//            }
//        } else if (mKeyboardType == PASSWORD) {
//            if (data.getText().toString().length()>=8){
//                isValid = true;
//            } else if (data.getText().toString().equalsIgnoreCase("")) {
//                error_msg.setText("Must not be empty");
//                isValid = false;
//            } else {
//                error_msg.setText("Must be at least 8 characters long");
//                isValid = false;
//            }
//            if (!isValid){
//                error.setVisibility(View.VISIBLE);
//            }
//        } else {
//            if (data.getText().toString().equalsIgnoreCase("")){
//                isValid = false;
//            } else {
//                isValid = true;
//            }
//            if (!isValid){
//                error_msg.setText("Must not be empty");
//                error.setVisibility(View.VISIBLE);
//            }
//        }
//
//    }

    public void showError() {
        title.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
        error_msg.setVisibility(View.VISIBLE);
    }

    public void hideError() {
        title.setVisibility(View.VISIBLE);
        error_msg.setVisibility(View.GONE);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        select(String.format("%02d",dayOfMonth) + "/" + String.format("%02d",month+1) + "/" + String.valueOf(year));
    }
}
