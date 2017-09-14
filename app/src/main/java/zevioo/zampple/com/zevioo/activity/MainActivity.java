package zevioo.zampple.com.zevioo.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.fragment.ProfileFragment;
import zevioo.zampple.com.zevioo.fragment.NotificationsFragment;
import zevioo.zampple.com.zevioo.fragment.SuggestionsFragment;
import zevioo.zampple.com.zevioo.fragment.FavoritesFragment;
import zevioo.zampple.com.zevioo.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {


    BottomNavigationViewEx  bottomNavigationView;
    Toolbar toolbar;
    Typeface custom_font;
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/hobo_std_medium.ttf");
        mTitle = (TextView) findViewById(R.id.title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigationView = (BottomNavigationViewEx)
                findViewById(R.id.navigation);
        initViews();

        // ADD badge
        addBadgeAt(3,100);
        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, SuggestionsFragment.newInstance());
        transaction.commit();
    }

    private void initViews(){
        mTitle.setText("zevioo");
        mTitle.setTypeface(custom_font);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.invite);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Click icon",Toast.LENGTH_LONG).show();
            }
        });
        getSupportActionBar().setTitle("");

        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.setSmallTextSize(9);
        bottomNavigationView.setLargeTextSize(9);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.suggest:
                                selectedFragment = SuggestionsFragment.newInstance();
                                break;
                            case R.id.search:
                                selectedFragment = SearchFragment.newInstance();
                                break;
                            case R.id.favorites:
                                selectedFragment = FavoritesFragment.newInstance();
                                break;
                            case R.id.notifications:
                                selectedFragment = NotificationsFragment.newInstance();
                                break;
                            case R.id.profile:
                                selectedFragment = ProfileFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
    }

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(bottomNavigationView.getBottomNavigationItemView(position));
//                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                    @Override
//                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
//                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem loginAction = menu.findItem(R.id.action_logo);
        loginAction.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}