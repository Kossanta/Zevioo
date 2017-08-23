package zevioo.zampple.com.zevioo.presenter;


import android.support.design.widget.Snackbar;

/**
 * Created by kgiannoulis on 2/6/2016
 */
public interface PresenterCallbacks {

    void noInternet();

    void somethingWrong(String msg);

    Snackbar pleaseWait(int id);

    void dismissWait(Snackbar bar);


}
