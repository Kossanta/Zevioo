package zevioo.zampple.com.zevioo.application;

import android.app.Application;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import zevioo.zampple.com.zevioo.tools.LocaleHelper;
import zevioo.zampple.com.zevioo.ws.WSTool;


/**
 * Created by kgiannoulis on 10/8/2017
 * Class that stores global application state
 */
public class ApplicationClass extends Application {

    /**
     * Object for accessing application preferences
     */
    private ApplicationPreferences appPrefs;

    private static ApplicationClass APPLICATION_CLASS;

    @Override
    public void onCreate() {
        super.onCreate();
        appPrefs = new ApplicationPreferences(this);
        APPLICATION_CLASS = this;
        // Setup handler for uncaught exceptions.
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                final Writer result = new StringWriter();
                final PrintWriter printWriter = new PrintWriter(result);
                ex.printStackTrace(printWriter);
                String stacktrace = result.toString();
                printWriter.close();
                //TODO log error
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        // TODO here get the users prefered language from shared preferencies
        super.attachBaseContext(LocaleHelper.onAttach(base, "el"));
    }

    public static ApplicationClass getInstance() {
        return APPLICATION_CLASS;
    }

    public ApplicationPreferences getAppPrefs() {
        return appPrefs;
    }

    public static void showMessage(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void inform(View layout, View.OnClickListener listener, String title, String action, int titleColor, int actionColor) {
        Snackbar snackbar;
        snackbar = Snackbar
                .make(layout, title, Snackbar.LENGTH_INDEFINITE)
                .setAction(action, listener);

        // Action button text color

        snackbar.setActionTextColor(actionColor);

        // Message text color
        View snackBarView = snackbar.getView();
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(titleColor);
        textView.setMaxLines(5);
        snackbar.show();
    }

    public static Snackbar inform(View layout, String title, int titleColor) {
        Snackbar snackbar;
        snackbar = Snackbar
                .make(layout, title, Snackbar.LENGTH_INDEFINITE);
        // Message text color
        View snackBarView = snackbar.getView();
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(titleColor);
        textView.setMaxLines(5);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar inform(View layout, int titleColor, int ws) {
        Snackbar snackbar;
        snackbar = Snackbar
                .make(layout, WSTool.getTitle(ws, getInstance()), Snackbar.LENGTH_INDEFINITE);
        // Message text color
        View snackBarView = snackbar.getView();
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(titleColor);
        textView.setMaxLines(5);
        snackbar.show();
        return snackbar;
    }
}
