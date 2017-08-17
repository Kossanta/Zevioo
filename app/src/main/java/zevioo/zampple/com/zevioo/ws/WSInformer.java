package zevioo.zampple.com.zevioo.ws;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kgiannoulis on 10/8/2017
 */
public interface WSInformer {

    void onStart(int ws);

    void onEnd(int ws, int type);

    void onSuccess(int ws, JSONObject response) throws JSONException;

    void onError(int ws, JSONObject response) throws JSONException;

    void onTimeout();
}
