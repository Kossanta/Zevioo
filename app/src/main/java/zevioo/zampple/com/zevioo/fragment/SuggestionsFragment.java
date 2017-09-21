package zevioo.zampple.com.zevioo.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.adapter.SuggestionsAdapter;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.presenter.SuggestionsFragmentPresenter;
import zevioo.zampple.com.zevioo.tools.InternetStatus;
import zevioo.zampple.com.zevioo.κουτί.entity.Product;

public class SuggestionsFragment extends LifecycleFragment{

    private SuggestionsAdapter mAdapter;
    private SuggestionsFragmentViewModel mViewModel;
    private SuggestionsFragmentPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private boolean refreshing;
    private LinearLayoutManager mLayoutManager;
    private RelativeLayout mNewLayout;
    private TextView mNewText;

    public static SuggestionsFragment newInstance() {
        SuggestionsFragment fragment = new SuggestionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshing = true;
        mPresenter = new SuggestionsFragmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.suggestions_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.suggestions_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (InternetStatus.getInstance(getActivity()).isOnline(getActivity())) {
                    refreshing = true;
                    mPresenter.refresh();
                } else {
                    refreshing = false;
                    noInternet();
                    mRefreshLayout.setRefreshing(refreshing);
                }
            }
        });
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int pastVisibleItems = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (pastVisibleItems  == 0) {
                    mNewLayout.setVisibility(View.GONE);
                    mNewLayout.clearAnimation();
                }
            }
        });
        mRefreshLayout.setRefreshing(refreshing);
        mNewLayout = (RelativeLayout) view.findViewById(R.id.new_layout);
        mNewText = (TextView) view.findViewById(R.id.new_text);
        mNewLayout.setVisibility(View.GONE);
        return view;
    }

    public void done() {
        if (refreshing) {
            refreshing = false;
        }
        mRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel = ViewModelProviders.of(this).get(SuggestionsFragmentViewModel.class);
        if (InternetStatus.getInstance(getActivity()).isOnline(getActivity())) {
            refreshing = true;
            mPresenter.refresh();
        } else {
            refreshing = false;
            noInternet();
        }
        mRefreshLayout.setRefreshing(refreshing);
        subscribeUiSuggestions();

    }

    private void subscribeUiSuggestions() {
        mViewModel.mProducts.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@NonNull final List<Product> products) {
                if (refreshing) {
                    refreshing = false;
                    mRefreshLayout.setRefreshing(refreshing);
                }
                updateList(products);
            }
        });
    }

    private void updateList(List<Product> list) {
        // todo update adapter
        if (mAdapter == null) {
            mAdapter = new SuggestionsAdapter(list, getActivity());
            mRecyclerView.setAdapter(mAdapter);
        } else {
            if (mLayoutManager.findFirstVisibleItemPosition() != 0) {
                // is not on top
                showNotifier(list.size() - mAdapter.getData().size());
            }
            mAdapter.setData(list);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void noInternet() {
        if (mAdapter != null) {
            if (mAdapter.getData().size() != 0) {
                // todo last synced
            } else {
                // todo show need internet connection
            }
        }
    }

    private void showNotifier(int newItems) {
        Animation fadeIn = new AlphaAnimation(0.0f,1.0f);
        fadeIn.setDuration(400);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mNewLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mNewText.setText(getActivity().getResources().getQuantityString(R.plurals.new_items,
                newItems, newItems));
        mNewLayout.startAnimation(fadeIn);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}