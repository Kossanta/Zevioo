package zevioo.zampple.com.zevioo.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import zevioo.zampple.com.zevioo.R;

/**
 * Created by kosaanta on 19/08/2017.
 */

public class StartPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> mTitles;
    ArrayList<Integer> mResources;
    Typeface custom_font;

    public StartPagerAdapter(Context context) {
        mContext = context;
        custom_font = Typeface.createFromAsset(mContext.getAssets(),  "fonts/hobo_std_medium.ttf");
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initResources();
    }

    private void initResources(){
        mTitles = new ArrayList<>();
        mTitles.add(mContext.getString(R.string.start_page_0_title));
        mTitles.add(mContext.getString(R.string.start_page_1_title));
        mTitles.add(mContext.getString(R.string.start_page_2_title));
        mResources = new ArrayList<>();
        mResources.add(R.drawable.start_page0);
        mResources.add(R.drawable.start_page1);
        mResources.add(R.drawable.start_page2);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.start_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.iv);
        imageView.setImageResource(mResources.get(position));
        TextView textView = (TextView) itemView.findViewById(R.id.tv);
        textView.setText(mTitles.get(position));
        textView.setTypeface(custom_font);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
