package zevioo.zampple.com.zevioo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by kgiannoulis on 5/7/2017
 */

public class CustomSpinner extends android.support.v7.widget.AppCompatSpinner {

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * An interface which a client of this Spinner could use to receive
     * open/closed events for this Spinner.
     */
    public interface OnSpinnerEventsListener {

        /**
         * Callback triggered when the spinner was opened.
         */
        void onSpinnerOpened(Spinner spinner);

        /**
         * Callback triggered when the spinner was closed.
         */
        void onSpinnerClosed(Spinner spinner);

    }

    private OnSpinnerEventsListener mListener;
    private boolean mOpenInitiated = false;

    // implement the Spinner constructors that you need

    @Override
    public boolean performClick() {
        // register that the Spinner was opened so we have a status
        // indicator for when the container holding this Spinner may lose focus
        mOpenInitiated = true;
        if (mListener != null) {
            mListener.onSpinnerOpened(this);
        }
        return super.performClick();
    }
    public void setSpinnerEventsListener(
            OnSpinnerEventsListener onSpinnerEventsListener) {
        mListener = onSpinnerEventsListener;
    }
    public void performClosedEvent() {
        mOpenInitiated = false;
        if (mListener != null) {
            mListener.onSpinnerClosed(this);
        }
    }

    public boolean hasBeenOpened() {
        return mOpenInitiated;
    }

    public void onWindowFocusChanged (boolean hasFocus) {
        if (hasBeenOpened() && hasFocus) {
            performClosedEvent();
        }
    }

}
