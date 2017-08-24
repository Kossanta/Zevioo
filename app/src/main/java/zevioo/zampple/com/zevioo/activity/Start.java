package zevioo.zampple.com.zevioo.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.adapter.StartPagerAdapter;

public class Start extends AppCompatActivity {

    StartPagerAdapter adapter;
    ViewPager pager;
    LinearLayout pagerDots;
    private ImageView[] ivArrayDotsPager;
    RelativeLayout login, register;
    TextView textaki, textaki1, title, subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pagerDots = (LinearLayout) findViewById(R.id.pager_dots);
        login = (RelativeLayout) findViewById(R.id.login);
        register = (RelativeLayout) findViewById(R.id.register);
        textaki = (TextView) findViewById(R.id.textaki);
        title = (TextView) findViewById(R.id.title);
        subtitle = (TextView) findViewById(R.id.subtitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/hobo_std_medium.ttf");

        textaki.setTypeface(custom_font);
        textaki1 = (TextView) findViewById(R.id.textaki1);
        title.setTypeface(custom_font);
        subtitle.setTypeface(custom_font);
        textaki1.setTypeface(custom_font);
//        login.setClickable(true);
        register.setClickable(true);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this,LoginActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this,RegistrationActivity.class));
            }
        });
        adapter = new StartPagerAdapter(this);
        pager.setAdapter(adapter);
        setupPagerIndidcatorDots();
        ivArrayDotsPager[0].setImageResource(R.drawable.selected_dot);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.default_dot);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.selected_dot);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        checkPermission();
    }

    private void setupPagerIndidcatorDots() {
        ivArrayDotsPager = new ImageView[adapter.getCount()];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.default_dot);
            //ivArrayDotsPager[i].setAlpha(0.4f);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    view.setAlpha(1);
                }
            });
            pagerDots.addView(ivArrayDotsPager[i]);
            pagerDots.bringToFront();
        }
    }

    private void checkPermission(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showMessageOKCancel(getString(R.string.write_permission),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(Start.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        1000);
                            }
                        });
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1000);
            }
        } else {
            // TODO GRANTED
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), okListener)
                .setNegativeButton(getString(R.string.cancel), null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1000:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO GRANTED
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
