package zevioo.zampple.com.zevioo.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class InternetStatus {


    private static InternetStatus instance = new InternetStatus();
    ConnectivityManager connectivityManager;
    static Context context;
    boolean connected = false;
//    Boolean access = null;

    public static InternetStatus getInstance(Context ctx) {
        context = ctx;
        return instance;
    }

    public boolean isOnline(Context con) {
        if (checkFirst(con)) {
            return Utils.INTERNET_ACCESS;
        } else {
            return connected;
        }

    }


    private boolean checkFirst(Context con) {
        try {
            connectivityManager = (ConnectivityManager) con
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();
            connected = networkInfo != null
                    && networkInfo.isAvailable() && networkInfo.isConnected();
            return connected;
        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }
}