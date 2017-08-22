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
public class Login extends AsyncTask<Void, Void, Void> {

    private String mRequest;
    private WSInformer mInformer;
    private Map<String, Object> mRequestList;

    public Login(WSInformer wsInformer, Map<String, Object> requestList) {
        this.mInformer = wsInformer;
        this.mRequestList = requestList;
        mRequest = getRequest();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mInformer.onStart(WSTool.LOGIN);
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        mInformer.onEnd(WSTool.LOGIN);
    }

    @Override
    protected Void doInBackground(Void... params) {
        sendData();
        return null;
    }


    private void sendData() {
        try {
            // 1. URL
            URL url = new URL(WSTool.SERVER_URL + "/login");
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
                mInformer.onSuccess(WSTool.LOGIN, responseJSON);
            } else {
                mInformer.onError(WSTool.LOGIN, responseJSON);
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
     * "EML": "spkaisaris@gmail.com",
     * "PSW": "12345678"
     *
     * Property Names:
     * EML = E-mail
     * PSW = Password
     *
     */
    private JSONObject getRequest1() throws JSONException {
        JSONObject object = new JSONObject();
        if (mRequestList.containsKey("EML")) {
            object.put("EML", mRequestList.get("EML"));
        }
        if (mRequestList.containsKey("PSW")) {
            object.put("PSW", mRequestList.get("PSW"));
        }
        return object;
    }


}
