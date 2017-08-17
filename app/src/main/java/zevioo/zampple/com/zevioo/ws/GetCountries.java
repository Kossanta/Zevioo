package zevioo.zampple.com.zevioo.ws;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;


/**
 * Created by kgiannoulis on 10/8/2017
 */
public class GetCountries extends AsyncTask<Void, Void, Void> {

    private String mRequest;
    private WSInformer mInformer;
    private Map<String, Object> mRequestList;
    private Context mContext;

    public GetCountries(WSInformer wsInformer, Map<String, Object> requestList, Context context) {
        this.mInformer = wsInformer;
        this.mRequestList = requestList;
        this.mContext = context;
    }

    public void init() {
        mRequest = getRequest();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mInformer.onStart(WSTool.GET_COUNTRIES);
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        mInformer.onEnd(WSTool.GET_COUNTRIES, 0);
    }

    @Override
    protected Void doInBackground(Void... params) {
        sendData();
        return null;
    }


    private void sendData() {
        try {
            // 1. URL
            URL url = new URL(WSTool.SERVER_URL + "/getcountries");
            // 2. Open connection
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            // 3. Specify POST method
            conn.setRequestMethod("POST");
            // 4. Set the headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.connect();
            // 5. Add JSON data into POST request body
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(mRequest);
            osw.flush();
            osw.close();
            // 6. Get the response
            int responseCode;
            try {
                responseCode = conn.getResponseCode();
            } catch (Exception e) {
                responseCode = 200;
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response1 = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            in.close();
            JSONObject responseJSON = new JSONObject(response1.toString());
            if (responseJSON.getInt("RES") == 0 && responseCode == 200) {
                mInformer.onSuccess(WSTool.GET_COUNTRIES, responseJSON);
            } else {
                mInformer.onError(WSTool.GET_COUNTRIES, responseJSON);
            }
        } catch (java.net.SocketTimeoutException e) {
            mInformer.onTimeout();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            // TODO HANDLE ERROR
            if (e instanceof SSLHandshakeException) {
                mInformer.onTimeout();
            }
        }
    }


    private String getRequest() {
        JSONObject request = new JSONObject();
        try {
            request = getRequest1();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return request.toString();
    }


    /**
     * "CID": "7962b7cb-616a-4dc8-9df0-86816a6e13ec"
     *
     * Property Names:
     * CID = Customer ID
     *
     */
    private JSONObject getRequest1() throws JSONException {
        JSONObject object = new JSONObject();
        ApplicationPreferences mPreferences = ((ApplicationClass) mContext.getApplicationContext()).getAppPrefs();
        object.put(ApplicationPreferences.CID, mPreferences.getStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.CID));
        return object;
    }


}
