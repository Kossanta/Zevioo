/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zevioo.zampple.com.zevioo.κουτί.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kgiannoulis on 18/8/2017
 */
@Entity(tableName = "Profile", foreignKeys = {
        @ForeignKey(entity = SimpleItem.class,
                parentColumns = "item_id",
                childColumns = "ctr",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = SimpleItem.class,
                parentColumns = "item_id",
                childColumns = "lng",
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value="ctr"),@Index(value="lng")})
public class Profile {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "ncm")
    private String nickname;
    @ColumnInfo(name = "dtx")
    private String description;
    private String cid;
    @ColumnInfo(name = "gn") // M = Male, F = Female
    private String gender;
    private Date dob;
    @ColumnInfo(name="ctr")
    private String countryId;
    @ColumnInfo(name="lng")
    private String languageId;
    @ColumnInfo(name="eml")
    private String email;
    @ColumnInfo(name = "psw")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile(String nickname, String description, String cid, String gender, Date dob, String countryId, String languageId, String email, String password) {
        this.nickname = nickname;
        this.description = description;
        this.cid = cid;
        this.gender = gender;
        this.dob = dob;
        this.countryId = countryId;
        this.languageId = languageId;
        this.email = email;
        this.password = password;
    }

    public JSONObject toJSON() throws JSONException {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        JSONObject object = new JSONObject();
        object.put("NCM",this.nickname);
        object.put("DTX",this.description);
        object.put("GN",this.gender);
        object.put("DOB",formater.format(this.dob));
        object.put("CTR",this.countryId);
        object.put("LNG",this.languageId);
        object.put("EML",this.email);
        object.put("PSW",this.password);
        return object;
    }
}
