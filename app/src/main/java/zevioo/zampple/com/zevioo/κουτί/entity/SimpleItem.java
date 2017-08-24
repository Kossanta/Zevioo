package zevioo.zampple.com.zevioo.κουτί.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONObject;

/**
 * Created by kgiannoulis on 10/8/2017
 */
@Entity(tableName = "SimpleItem",indices = {@Index(value = {"item_id"},
                unique = true)})
public class SimpleItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    String code;
    String item_id;
    String name;
    int type;
    public static int LANGUAGE = 1;
    public static int COUNTRY = 2;

    public SimpleItem() {
        initValues();
    }

    private void initValues() {
        code = "";
        item_id = "";
        name = "";
        type = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public SimpleItem fromJSON(JSONObject object, int type){
        this.code = object.optString("CD");
        this.name = object.optString("NM");
        this.item_id = object.optString("ID");
        this.type = type;
        return this;
    }
}
