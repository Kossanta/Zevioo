package zevioo.zampple.com.zevioo.fragment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.κουτί.ZeviooDb;
import zevioo.zampple.com.zevioo.κουτί.entity.Product;

public class SuggestionsFragmentViewModel extends AndroidViewModel {

    public final LiveData<List<Product>> mProducts;
    ZeviooDb mDb;

    public SuggestionsFragmentViewModel(Application application) {
        super(application);
        mDb = ZeviooDb.getDatabase(application);
        mProducts = mDb.productModel().findAllProducts();
    }
}