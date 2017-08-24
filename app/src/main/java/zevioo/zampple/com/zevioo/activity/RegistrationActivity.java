package zevioo.zampple.com.zevioo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.presenter.PresenterCallbacks;
import zevioo.zampple.com.zevioo.presenter.RegistrationActivityPresenter;
import zevioo.zampple.com.zevioo.presenter.Validator;
import zevioo.zampple.com.zevioo.view.ButtonTextView;
import zevioo.zampple.com.zevioo.view.EditView;
import zevioo.zampple.com.zevioo.view.Spinner;

public class RegistrationActivity extends AppCompatActivity implements Validator, PresenterCallbacks {

    EditView mNickName;
    EditView mDescription;
    EditView mPassword;
    EditView mEmail;
    ButtonTextView mDob;
    Toolbar toolbar;
    RelativeLayout mMale, mFemale;
    ImageView mMaleImg, mFemaleImg, mTermsImg;
    TextView mMaleText, mFemaleText;
    Spinner mCountries, mLanguages;
    String mGenderValue;
    private int maxFields = 8;
    private int currentFilledFields = 0;
    private boolean proceed,agree_terms;
    RegistrationActivityPresenter presenter;
    CoordinatorLayout parent_layout;
    RelativeLayout terms_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        proceed = false;
        mGenderValue = "";
        agree_terms = false;
        parent_layout = (CoordinatorLayout) findViewById(R.id.parent_layout);
        mTermsImg = (ImageView) findViewById(R.id.terms_check);
        terms_layout = (RelativeLayout) findViewById(R.id.terms_layout);
        terms_layout.setClickable(true);
        mNickName = (EditView) findViewById(R.id.nickname);
        mDescription = (EditView) findViewById(R.id.description);
        mPassword = (EditView) findViewById(R.id.password);
        mEmail = (EditView) findViewById(R.id.email);
        mMale = (RelativeLayout) findViewById(R.id.male);
        mFemale = (RelativeLayout) findViewById(R.id.female);
        mMaleImg = (ImageView) findViewById(R.id.male_img);
        mMaleText = (TextView) findViewById(R.id.male_text);
        mFemaleImg = (ImageView) findViewById(R.id.female_img);
        mFemaleText = (TextView) findViewById(R.id.female_text);
        mCountries = (Spinner) findViewById(R.id.country);
        mCountries.init("Select a country","Must not be empty", Spinner.COUNTRIES,this);
        mLanguages = (Spinner) findViewById(R.id.language);
        mLanguages.init("Select a language","Must not be empty", Spinner.LANGUAGES,this);
        mDob = (ButtonTextView) findViewById(R.id.dob);
        mDob.init(getString(R.string.registration_dob),ButtonTextView.DOB, this);
        mMale.setClickable(true);
        mFemale.setClickable(true);
        mNickName.init(getString(R.string.registration_nick),EditView.NICKNAME,this);
        mDescription.init(getString(R.string.registration_desc),EditView.TEXT,this);
        mPassword.init(getString(R.string.registration_pass),EditView.PASSWORD,this);
        mEmail.init(getString(R.string.registration_email),EditView.EMAIL,this);
        mMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaleImg.setImageResource(R.drawable.gender_selected);
                mFemaleImg.setImageResource(R.drawable.gender_empty);
                mMaleText.setTextColor(ContextCompat.getColor(RegistrationActivity.this, R.color.black));
                mFemaleText.setTextColor(ContextCompat.getColor(RegistrationActivity.this, R.color.lighter_gray));
                if (mGenderValue.equalsIgnoreCase("")){
                    valid();
                }
                mGenderValue = "M";
            }
        });
        mFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFemaleImg.setImageResource(R.drawable.gender_selected);
                mMaleImg.setImageResource(R.drawable.gender_empty);
                mFemaleText.setTextColor(ContextCompat.getColor(RegistrationActivity.this, R.color.black));
                mMaleText.setTextColor(ContextCompat.getColor(RegistrationActivity.this, R.color.lighter_gray));
                if (mGenderValue.equalsIgnoreCase("")){
                    valid();
                }
                mGenderValue = "F";

            }
        });
        terms_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (agree_terms){
                    mTermsImg.setImageResource(R.drawable.gender_empty);
                    agree_terms = false;
                    proceed = false;
                    invalidateOptionsMenu();
                } else {
                    if (currentFilledFields==maxFields){
                        proceed = true;
                        invalidateOptionsMenu();
                    }
                    agree_terms = true;
                    mTermsImg.setImageResource(R.drawable.gender_selected);
                }
            }
        });
        presenter = new RegistrationActivityPresenter(this);
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        MenuItem loginAction = menu.findItem(R.id.action_register);
        loginAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mEmail.isValid() && mPassword.isValid()
                        && mNickName.isValid()
                        && mDescription.isValid()
                        && !mGenderValue.equalsIgnoreCase("")
                        && mDob.isValid()
                        && mCountries.isValid()
                        && mLanguages.isValid()){
                    presenter.sendRegistration(mNickName.getValue(),
                            mDescription.getValue(),
                            mGenderValue,mDob.getValue(),
                            mCountries.getValueId(),mLanguages.getValueId(),
                            mEmail.getValue(),mPassword.getValue());
                } else {
                    mEmail.validate();
                    mPassword.validate();
                    mNickName.validate();
                    mDescription.validate();
                    mDob.validate();
                }
                return false;
            }
        });
        if (proceed){
            menu.findItem(R.id.action_register).setVisible(true);
        } else {
            menu.findItem(R.id.action_register).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (proceed){
            menu.findItem(R.id.action_register).setVisible(true);
        } else {
            menu.findItem(R.id.action_register).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void invalid() {
        currentFilledFields--;
        proceed = false;
        invalidateOptionsMenu();
    }

    @Override
    public void valid() {
        currentFilledFields++;
        if (currentFilledFields==maxFields && agree_terms){
            proceed = true;
            invalidateOptionsMenu();
        }
    }

    public void success(){
        // TODO move to next activity main
    }

    @Override
    public void noInternet() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ApplicationClass.inform(parent_layout, getString(R.string.snack_no_internet), Color.YELLOW);
            }
        });
    }

    @Override
    public void somethingWrong(String msg) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmail.isValid() && mPassword.isValid()
                        && mNickName.isValid()
                        && mDescription.isValid()
                        && !mGenderValue.equalsIgnoreCase("")
                        && mDob.isValid()
                        && mCountries.isValid()
                        && mLanguages.isValid()){
                    presenter.sendRegistration(mNickName.getValue(),mDescription.getValue(),mGenderValue,mDob.getValue(),mCountries.getValueId(),mLanguages.getValueId(),mEmail.getValue(),mPassword.getValue());
                } else {
                    mEmail.validate();
                    mPassword.validate();
                    mNickName.validate();
                    mDescription.validate();
                    mDob.validate();
                }
            }
        };
        ApplicationClass.inform(parent_layout, clickListener,
                msg
                , getString(R.string.snack_action), Color.RED, Color.WHITE);
    }

    @Override
    public Snackbar pleaseWait(int id) {
        return ApplicationClass.inform(parent_layout, Color.WHITE, id);
    }

    @Override
    public void dismissWait(Snackbar bar) {
            bar.dismiss();
    }
}