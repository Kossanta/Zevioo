package zevioo.zampple.com.zevioo.ws;

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


/**
 * Created by kgiannoulis on 10/8/2017
 */
public class Register extends AsyncTask<Void, Void, Void> {

    private String mRequest;
    private WSInformer mInformer;
    private Map<String, Object> mRequestList;

    public Register(WSInformer wsInformer, Map<String, Object> requestList) {
        this.mInformer = wsInformer;
        this.mRequestList = requestList;
    }

    public void init() {
        mRequest = getRequest();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mInformer.onStart(WSTool.REGISTRATION);
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        mInformer.onEnd(WSTool.REGISTRATION, 0);
    }

    @Override
    protected Void doInBackground(Void... params) {
        sendData();
        return null;
    }


    private void sendData() {
        try {
            // 1. URL
            URL url = new URL(WSTool.SERVER_URL + "/register");
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
                mInformer.onSuccess(WSTool.REGISTRATION, responseJSON);
            } else {
                mInformer.onError(WSTool.REGISTRATION, responseJSON);
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
     * "NCM": "My Nickname"
     * "DTX": "My describe text",
     * "GN": "M",
     * "DOB": "30/06/1979",
     * "CTR": "3da3e4ba-064e-4cea-b820-7a2f33d60394",
     * "LNG": "f721cb6f-0eed-48ff-a0d0-1021db054000",
     * "EML": "spkaisaris@gmail.com",
     * "PSW": "12345678"
     *
     * Property Names:
     * NCM = Nickname
     * DTX = Describe text
     * GN = Gender
     * DOB = Date of birth
     * CTR = SimpleItem ID
     * LNG = Language ID
     * EML = E-mail
     * PSW = Password
     *
     */
    private JSONObject getRequest1() throws JSONException {
        JSONObject object = new JSONObject();
        if (mRequestList.containsKey("NCM")) {
            object.put("NCM", mRequestList.get("NCM"));
        }
        if (mRequestList.containsKey("DTX")) {
            object.put("DTX", mRequestList.get("DTX"));
        }
        if (mRequestList.containsKey("GN")) {
            object.put("GN", mRequestList.get("GN"));
        }
        if (mRequestList.containsKey("DOB")) {
            object.put("DOB", mRequestList.get("DOB"));
        }
        if (mRequestList.containsKey("CTR")) {
            object.put("CTR", mRequestList.get("CTR"));
        }
        if (mRequestList.containsKey("LNG")) {
            object.put("LNG", mRequestList.get("LNG"));
        }
        if (mRequestList.containsKey("EML")) {
            object.put("EML", mRequestList.get("EML"));
        }
        if (mRequestList.containsKey("PSW")) {
            object.put("PSW", mRequestList.get("PSW"));
        }
        return object;
    }


}
