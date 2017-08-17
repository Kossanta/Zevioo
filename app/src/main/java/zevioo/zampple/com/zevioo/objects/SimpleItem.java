package zevioo.zampple.com.zevioo.objects;

import org.json.JSONObject;

/**
 * Created by kgiannoulis on 10/8/2017
 */
public class SimpleItem {

    String mCD;
    String mID;
    String mNM;

    public SimpleItem() {
        initValues();
    }

    private void initValues() {
        mCD = "";
        mNM = "";
        mID = "";
    }

    public String getmCD() {
        return mCD;
    }

    public void setmCD(String mCD) {
        this.mCD = mCD;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmNM() {
        return mNM;
    }

    public void setmNM(String mNM) {
        this.mNM = mNM;
    }

    @Override
    public String toString() {
        return mNM;
    }

    public SimpleItem fromJSON(JSONObject object){
        this.mCD = object.optString("CD");
        this.mNM = object.optString("NM");
        this.mID = object.optString("ID");
        return this;
    }
}
