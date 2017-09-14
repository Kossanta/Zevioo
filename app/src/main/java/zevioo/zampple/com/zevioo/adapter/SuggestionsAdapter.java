package zevioo.zampple.com.zevioo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.κουτί.entity.Product;

/**
 * Created by kgiannoulis on 14/09/2017.
 */
public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.SuggestHolder> {

    private List<Product> mProductList;
    private int lastPosition = -1;
    private Activity activity;


    public SuggestionsAdapter(List<Product> list, final Activity activity) {
        this.mProductList = list;
        this.activity = activity;
    }

    public List<Product> getData() {
        return mProductList;
    }

    public void setData(List<Product> list) {
        this.mProductList = list;
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    @Override
    public void onBindViewHolder(final SuggestHolder holder, int i) {

        final Product product = mProductList.get(i);
        holder.mUserName.setText(product.getNameOfUser());
        holder.mGrade.setText(String.valueOf(product.getGrade()));
        holder.mStar.setImageResource(calculateStar(holder,product.getGrade()));
        addComments(holder,product);
        Picasso.with(activity).load(product.getProductImageUrl()).resize(ApplicationClass.dpToPx(240),ApplicationClass.dpToPx(240)).centerCrop()
                .into(holder.mProductImage);
        Picasso.with(activity).load(product.getProductImageUrl()).resize(ApplicationClass.dpToPx(64),ApplicationClass.dpToPx(64)).centerCrop()
                .into(holder.mSmallProductImage);
        // todo load views
//        setAnimation(badgeHolder,i);
    }

    private void addComments(SuggestHolder holder, Product product){
        if (product.getNegativeComment().equalsIgnoreCase("")){
            holder.mCons.setVisibility(View.GONE);
            holder.mConsComment.setVisibility(View.GONE);
        } else {
            holder.mCons.setVisibility(View.VISIBLE);
            holder.mConsComment.setVisibility(View.VISIBLE);
            holder.mConsComment.setText(product.getNegativeComment());
        }
        if (product.getPositiveComment().equalsIgnoreCase("")){
            holder.mPros.setVisibility(View.GONE);
            holder.mProsCommnet.setVisibility(View.GONE);
        } else {
            holder.mPros.setVisibility(View.VISIBLE);
            holder.mProsCommnet.setVisibility(View.VISIBLE);
            holder.mProsCommnet.setText(product.getPositiveComment());
        }
    }

    private int calculateStar(SuggestHolder holder,int grade){
        switch (grade){
            case 1:
                holder.mStar1.setImageResource(R.drawable.star_white);
                holder.mStar2.setImageResource(R.drawable.star_empty);
                holder.mStar3.setImageResource(R.drawable.star_empty);
                holder.mStar4.setImageResource(R.drawable.star_empty);
                holder.mStar5.setImageResource(R.drawable.star_empty);
                return R.drawable.star_worst;
            case 2:
                holder.mStar1.setImageResource(R.drawable.star_white);
                holder.mStar2.setImageResource(R.drawable.star_white);
                holder.mStar3.setImageResource(R.drawable.star_empty);
                holder.mStar4.setImageResource(R.drawable.star_empty);
                holder.mStar5.setImageResource(R.drawable.star_empty);
                return R.drawable.star_bad;
            case 3:
                holder.mStar1.setImageResource(R.drawable.star_white);
                holder.mStar2.setImageResource(R.drawable.star_white);
                holder.mStar3.setImageResource(R.drawable.star_white);
                holder.mStar4.setImageResource(R.drawable.star_empty);
                holder.mStar5.setImageResource(R.drawable.star_empty);
                return R.drawable.star_avg;
            case 4:
                holder.mStar1.setImageResource(R.drawable.star_white);
                holder.mStar2.setImageResource(R.drawable.star_white);
                holder.mStar3.setImageResource(R.drawable.star_white);
                holder.mStar4.setImageResource(R.drawable.star_white);
                holder.mStar5.setImageResource(R.drawable.star_empty);
                return R.drawable.star_good;
            case 5:
                holder.mStar1.setImageResource(R.drawable.star_white);
                holder.mStar2.setImageResource(R.drawable.star_white);
                holder.mStar3.setImageResource(R.drawable.star_white);
                holder.mStar4.setImageResource(R.drawable.star_white);
                holder.mStar5.setImageResource(R.drawable.star_white);
                return R.drawable.star_excellent;
            default:
                holder.mStar1.setImageResource(R.drawable.star_white);
                holder.mStar2.setImageResource(R.drawable.star_white);
                holder.mStar3.setImageResource(R.drawable.star_white);
                holder.mStar4.setImageResource(R.drawable.star_empty);
                holder.mStar5.setImageResource(R.drawable.star_empty);
                return R.drawable.star_avg;
        }
    }


    @Override
    public SuggestHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.suggestion_user, viewGroup, false);
        SuggestHolder vh = new SuggestHolder(v);
        return vh;
    }


    public static class SuggestHolder extends RecyclerView.ViewHolder {

        protected TextView mUserName, mGrade, mProsCommnet, mConsComment;
        protected ImageView mStar, mPros, mCons, mProductImage, mSmallProductImage;
        protected ImageView mStar1,mStar2,mStar3,mStar4,mStar5;
        protected RelativeLayout mFrame;


        public SuggestHolder(View v) {
            super(v);
            mUserName = (TextView) v.findViewById(R.id.user_name);
            mGrade = (TextView) v.findViewById(R.id.grade);
            mStar = (ImageView) v.findViewById(R.id.star);
            mPros = (ImageView) v.findViewById(R.id.pros_icon);
            mProsCommnet = (TextView) v.findViewById(R.id.pros_comment);
            mCons = (ImageView) v.findViewById(R.id.cons_icon);
            mConsComment = (TextView) v.findViewById(R.id.cons_comment);
            mProductImage = (ImageView) v.findViewById(R.id.product_image);
            mSmallProductImage = (ImageView) v.findViewById(R.id.small_image);
            mFrame = (RelativeLayout) v.findViewById(R.id.frame);
            mStar1 = (ImageView) v.findViewById(R.id.star1);
            mStar2 = (ImageView) v.findViewById(R.id.star2);
            mStar3 = (ImageView) v.findViewById(R.id.star3);
            mStar4 = (ImageView) v.findViewById(R.id.star4);
            mStar5 = (ImageView) v.findViewById(R.id.star5);
        }
    }

//    private void setAnimation(SuggestHolder holder, int position) {
//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(activity, R.anim.pop);
//            animation.setStartOffset(300);
//            holder.icon.startAnimation(animation);
//        }
//        lastPosition = position;
//    }
}
