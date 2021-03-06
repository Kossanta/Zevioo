package zevioo.zampple.com.zevioo.ws;

import android.content.Context;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import zevioo.zampple.com.zevioo.R;
import zevioo.zampple.com.zevioo.activity.RegistrationActivity;
import zevioo.zampple.com.zevioo.activity.ValidateActivity;
import zevioo.zampple.com.zevioo.application.ApplicationClass;
import zevioo.zampple.com.zevioo.application.ApplicationPreferences;
import zevioo.zampple.com.zevioo.κουτί.Executor;
import zevioo.zampple.com.zevioo.κουτί.entity.Product;
import zevioo.zampple.com.zevioo.κουτί.entity.SimpleItem;


/**
 * Created by kgiannoulis on 10/8/2017
 */
public class WSTool {

    // ws types
    public static final int REGISTRATION = 1;
    public static final int UPDATE_PROFILE = 2;
    public static final int LOGIN = 3;
    public static final int CHECK_NICKNAME = 4;
    public static final int CHECK_VFCODE = 5;
    public static final int GET_COUNTRIES = 6;
    public static final int GET_LANGUAGES = 7;
    public static final int GET_SUGGESTIONS = 8;


    public static final String SERVER_URL = "https://api.zevioo.com/mobile.svc"; //live server

    static String HOSTNAME = "api.zevioo.com";

    private Context mContext;

    public WSTool(Context context) {
        this.mContext = context;
    }

    /**
     * Property Names:
     * RES = Result (0 = Nick name not in use, 1 = Nickname already used, -1 = Error)
     * MSG = Server message
     *
     * {
     * "RES": 0,
     * "MSG": ""
     * }
     * @param response
     * @throws JSONException
     */

    public void parseGenericResponse(JSONObject response) throws JSONException {
        int res = response.getInt("RES");
        String msg = response.getString("MSG");
    }

    /**
     * Property Names:
     * CID = Customer ID
     * NCM = Nickname
     * DTX = Describe text
     * GN = Gender
     * DOB = Date of birth
     * CTR = SimpleItem ID
     * LNG = Language ID
     * EML = E-mail
     * PSW = Password
     * VC = Verification Code
     *
     * {
     * "CID": "7962b7cb-616a-4dc8-9df0-86816a6e13ec"
     * "NCM": "My Nickname",
     * "DTX": "My describe text",
     * "GN": "M",
     * "DOB": "30/06/1979",
     * "CTR": "3da3e4ba-064e-4cea-b820-7a2f33d60394",
     * "LNG": "f721cb6f-0eed-48ff-a0d0-1021db054000",
     * "EML": "spkaisaris@gmail.com",
     * "PSW": "12345678",
     * "VC": "1294"
     * }
     * @param response
     * @throws JSONException
     */

    public void parseLoginResponse(JSONObject response) throws JSONException {
        ApplicationPreferences preferences = ((ApplicationClass) mContext.getApplicationContext()).getAppPrefs();
        String cid = response.getString("CID");
        String vc = response.getString("VC");
        // TODO get profile and save it to database
        preferences.saveStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.CID, cid);
        preferences.saveStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.LOGGEDIN, "1");
    }

    /**
     * Property Names:
     * CID = Customer ID
     * RES = Result (0 = Success, 1 = Warning, -1 = Error)
     * MSG = Server message
     *
     * {
     *   "CID":"7962b7cb-616a-4dc8-9df0-86816a6e13ec",
     *   "RES":0,
     *   "MSG":""
     * }
     * @param response
     * @throws JSONException
     */
    public void parseRegisterUpdateResponse(JSONObject response) throws JSONException{
        ApplicationPreferences preferences = ((ApplicationClass) mContext.getApplicationContext()).getAppPrefs();
        String cid = response.getString("CID");
        preferences.saveStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.CID, cid);
        preferences.saveStringPreference(ApplicationPreferences.PERSONAL_PREFS, ApplicationPreferences.LOGGEDIN, "1");
    }

    /**
     * * used for countries and languages
     * Property Names:
     * RES = Result (0 = Success, 1 = Warning, -1 = Error)
     * MSG = Server message
     * LST = SimpleItems array
     * CD = Code
     * ID = SimpleItem ID
     * NM = Name
     *
     * {
     * "MSG": "",
     * "RES": 0,
     * "LST": [
     * {
     * "CD": "GRC",
     * "ID": "3da3e4ba-064e-4cea-b820-7a2f33d60394",
     * "NM": "Ελλάδα"
     * }
     * ]
     * }
     * @param response
     * @throws JSONException
     */
    public ArrayList<SimpleItem> parseSimpleItem(JSONObject response, int type) throws JSONException{
        ArrayList<SimpleItem> listOfCountries = new ArrayList<>();
        JSONArray countriesJSON = response.getJSONArray("LST");
        for (int i=0; i<countriesJSON.length();i++) {
            JSONObject tempCountry = countriesJSON.getJSONObject(i);
            SimpleItem simpleItem = new SimpleItem();
            listOfCountries.add(simpleItem.fromJSON(tempCountry,type));
        }
        return listOfCountries;
    }

    /**
     * * used for getting suggestions list
     * Property Names:
     * RES = Result (0 = Success, 1 = Warning, -1 = Error)
     * MSG = Server message
     * ITEMS = Product array
     * CIMG = user image url (or avatar)
     * DTX = description of user
     * EAN = product barcode
     * NASW = negative comment
     * NCM = name of user
     * OCM = overall comments ??
     * ORT = grade
     * PASW = positive comments
     * PIMG = product image
     * PNM = product title
     * PRC = price
     * PRID = product id
     * RDT = posted date dd/MM/yyyy HH:mm:ss
     * SIMG = e-shop image url
     * SNM = e-shop name
     * SURL = e-shop url
     *
     * {
     * "MSG": "",
     * "RES": 0,
     * "ITEMS": [
     *  {
     "CIMG": "",
     "DTX": "",
     "EAN": "3508240000875",
     "NASW": "",
     "NCM": "ΙΟΥΛΊΑ Δ.",
     "OCM": "",
     "ORT": 5,
     "PASW": "Αναλαφρη, απορροφαται αμέσως, δεν είναι καθόλου λιπαρη και έχει αισθητή διαφορα το δέρμα μου μετά από ενάμιση μήνα που την χρησιμοποιώ!\r\nΑπλά δεν την αλλάζω!",
     "PIMG": "http://assets.gy.digital/RxvEi-uGWRAcf4876qtPwxFQSYc=/fit-in/350x350/filters:fill(white)/s3.gy.digital/pharmacy2go/uploads/asset/data/17141/L0036_-_SUNISSIME_-_VISAGE_SPF_30.jpg",
     "PNM": "Lierac Sunissime Αντηλιακή λεπτόρρευστη κρέμα προστασίας SPF30 ολικής αντιγήρανσης",
     "PRC": 16.5,
     "PRID": "8a39c598-8a14-440b-b5a6-d7578fb817da",
     "RDT": "16/08/2017 09:59:16",
     "SIMG": "pharmacy2go.png",
     "SNM": "Pharmacy2Go",
     "SURL": "https://www.pharmacy2go.gr/"
     } ....
     * ],
     * "TCNT":1003
     * }
     * @param response
     * @throws JSONException
     */
    public
//    ArrayList<Product>
    void parseSuggestions(JSONObject response
           // , final Executor.Result result
    ) throws JSONException{
        ArrayList<Product> listOfProducts = new ArrayList<>();
        JSONArray list = response.getJSONArray("ITEMS");
        for (int i=0; i<list.length();i++) {
            JSONObject tempProduct = list.getJSONObject(i);
            Product product = new Product();
            listOfProducts.add(product.fromJSON(tempProduct));
        }
        new Executor(mContext, new Executor.Result() {
            @Override
            @SuppressWarnings("unchecked")
            public void onResultList(List listResult) {
            }

            @Override
            public void onResultItem(Object item) {
            }

            @Override
            public void insertedOk(long insertedId) {
            }
            @Override
            public void actionOk() {
//                // TODO inserted
//                result.actionOk();

            }
        }).addProducts(listOfProducts);

//        return listOfProducts;
    }



    public static String getTitle(int id, ApplicationClass clas) {
        String message = "";
        switch (id) {
            case 1:
                message = clas.getString(R.string.msg1);
                break;
            case 2:
                message = clas.getString(R.string.msg2);
                break;
            case 3:
                message = clas.getString(R.string.msg3);
                break;
            case 5:
                message = clas.getString(R.string.msg5);
                break;
        }
        return message;
    }

    public static SSLSocketFactory createSslSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
        TrustManager[] byPassTrustManagers = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, byPassTrustManagers, new SecureRandom());
        return sslContext.getSocketFactory();
    }

    public static HostnameVerifier verifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                HostnameVerifier hv =
                        HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify(HOSTNAME, session);
            }
        };
    }


}
