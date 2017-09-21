package zevioo.zampple.com.zevioo.adapter;

import android.app.Activity;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.view.CircleTransform;
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
        if (!product.getUserImageUrl().equalsIgnoreCase("")){
            Picasso.with(activity).load(product.getUserImageUrl()).resize(ApplicationClass.dpToPx(32),ApplicationClass.dpToPx(32)).centerCrop().transform(new CircleTransform())
                    .into(holder.mUserAvatar);
        }
        holder.mFollow.setTag(product);
        holder.mFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo here follow member WS
//                showActions((Product)v.getTag());
            }
        });
        if (!product.getProductImageUrl().equalsIgnoreCase("")) {
            Picasso.with(activity).load(product.getProductImageUrl()).resize(ApplicationClass.dpToPx(240), ApplicationClass.dpToPx(240)).centerCrop()
                    .into(holder.mProductImage);
            Picasso.with(activity).load(product.getProductImageUrl()).resize(ApplicationClass.dpToPx(64), ApplicationClass.dpToPx(64)).centerCrop()
                    .into(holder.mSmallProductImage);
        }
        holder.mUserDescription.setText(product.getDescriptionOfUser().equalsIgnoreCase("") ? "Dummy description" : product.getDescriptionOfUser());
        // todo show hide valid purchase
//        if (product.isValid()){
//            holder.mVerifiedText.setVisibility(View.VISIBLE);
//            holder.mVerifiedIcon.setVisibility(View.VISIBLE);
//        } else {
//            holder.mVerifiedText.setVisibility(View.GONE);
//            holder.mVerifiedIcon.setVisibility(View.GONE);
//        }
        // todo load users image if any
        Picasso.with(activity).load("https://cdn.pixabay.com/photo/2014/11/28/01/01/jay-548381_960_720.jpg").resize(ApplicationClass.dpToPx(16),ApplicationClass.dpToPx(16)).centerCrop().transform(new CircleTransform())
                .into(holder.mUser1);
        Picasso.with(activity).load("https://cdn.pixabay.com/photo/2014/03/23/09/38/swan-293157_960_720.jpg").resize(ApplicationClass.dpToPx(16),ApplicationClass.dpToPx(16)).centerCrop().transform(new CircleTransform())
                .into(holder.mUser2);
        Picasso.with(activity).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUSaoTcU7EiRBKlB26__J_YhAXK5WUjffGO9lQZ5bHgJs5Boybzg").resize(ApplicationClass.dpToPx(16),ApplicationClass.dpToPx(16)).centerCrop().transform(new CircleTransform())
                .into(holder.mUser3);
        Picasso.with(activity).load("http://www.mybligr.com/wp-content/uploads/2017/01/most-beautiful-tiger-animals-pics-images-photos-pictures-6.jpg").resize(ApplicationClass.dpToPx(16),ApplicationClass.dpToPx(16)).centerCrop().transform(new CircleTransform())
                .into(holder.mUser4);
        Picasso.with(activity).load("https://images-na.ssl-images-amazon.com/images/I/51udtcfUllL._CR0,124,776,776_UX128.jpg").resize(ApplicationClass.dpToPx(16),ApplicationClass.dpToPx(16)).centerCrop().transform(new CircleTransform())
                .into(holder.mUser5);
        Picasso.with(activity).load("https://www.html5rocks.com/static/images/tutorials/easy-hidpi/chrome1x.png").resize(ApplicationClass.dpToPx(16),ApplicationClass.dpToPx(16)).centerCrop().transform(new CircleTransform())
                .into(holder.mUser6);
        holder.mTotalComments.setText("(" + (product.getOverallComments().equalsIgnoreCase("")?"48":product.getOverallComments()) + ")");
        holder.mProductName.setText(product.getProductName());
        // todo get product category
//        holder.mProductCategory.setText(product.getProductCategory());
        holder.mTotalGrade.setText(String.valueOf(product.getGrade()));
        // todo get total positive comments
//        holder.mTotalPositive.setText(String.valueOf(product.getTotalPositiveComments()));
        // todo get total negative comments
//        holder.mTotalPositive.setText(String.valueOf(product.getTotalNegativeComments()));
        holder.mTimestamp.setText(product.calculateTime(activity));

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

    // todo this one is to show actions
    private void showActions(Product product){
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(activity);
        View sheetView = activity.getLayoutInflater().inflate(R.layout.actions_layout, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
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

        protected TextView mUserName, mGrade, mProsCommnet, mConsComment, mVerifiedText, mProductName,mProductCategory,mTotalGrade, mTotalComments, mTotalPositive, mTotalNegative, mTimestamp, mUserDescription;
        protected ImageView mStar, mPros, mCons, mProductImage, mSmallProductImage, mActions, mVerifiedIcon, mArrowImg, mLike, mDisLike, mComments;
        protected ImageView mUser1, mUser2, mUser3, mUser4, mUser5, mUser6, mUserAvatar;
        protected ImageView mStar1,mStar2,mStar3,mStar4,mStar5;
        protected RelativeLayout mFrame;
        protected ProgressBar mProgress;
        protected Button mFollow;


        public SuggestHolder(View v) {
            super(v);
            mUserDescription = (TextView) v.findViewById(R.id.user_description);
            mTimestamp = (TextView) v.findViewById(R.id.timestamp);
            mVerifiedText = (TextView) v.findViewById(R.id.verified_text);
            mProductName = (TextView) v.findViewById(R.id.product_name);
            mProductCategory = (TextView) v.findViewById(R.id.product_category);
            mTotalGrade = (TextView) v.findViewById(R.id.total_grade);
            mTotalComments = (TextView) v.findViewById(R.id.total_comments);
            mTotalPositive = (TextView) v.findViewById(R.id.total_positive);
            mTotalNegative = (TextView) v.findViewById(R.id.total_negative);

            mUserAvatar = (ImageView) v.findViewById(R.id.user_avatar);
            mVerifiedIcon = (ImageView) v.findViewById(R.id.verified_icon);
            mArrowImg = (ImageView) v.findViewById(R.id.arrow_img);
            mLike = (ImageView) v.findViewById(R.id.like_icon);
            mUser1 = (ImageView) v.findViewById(R.id.user1);
            mUser2 = (ImageView) v.findViewById(R.id.user2);
            mUser3 = (ImageView) v.findViewById(R.id.user3);
            mUser4 = (ImageView) v.findViewById(R.id.user4);
            mUser5 = (ImageView) v.findViewById(R.id.user5);
            mUser6 = (ImageView) v.findViewById(R.id.user6);
            mDisLike = (ImageView) v.findViewById(R.id.dislike_icon);
            mComments = (ImageView) v.findViewById(R.id.comment);

            mFollow = (Button) v.findViewById(R.id.btn_follow);
            mProgress = (ProgressBar) v.findViewById(R.id.progress);
            mProgress.setVisibility(View.GONE);
            mActions = (ImageView) v.findViewById(R.id.actions);
            mActions.setVisibility(View.GONE);
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
