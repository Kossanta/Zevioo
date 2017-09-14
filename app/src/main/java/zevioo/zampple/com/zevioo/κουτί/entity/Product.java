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
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kgiannoulis on 18/8/2017
 */
@Entity(tableName = "Product")
public class Product {
    @PrimaryKey
    @ColumnInfo(name = "prid")
    private String productId;
    @ColumnInfo(name = "cimg")
    private String userImageUrl;
    @ColumnInfo(name = "dtx")
    private String descriptionOfUser;
    @ColumnInfo(name = "ean")
    private String productBarcode;
    @ColumnInfo(name = "nasw")
    private String negativeComment;
    @ColumnInfo(name = "ncm")
    private String nameOfUser;
    @ColumnInfo(name = "ocm")
    private String overallComments;
    @ColumnInfo(name = "ort")
    private int grade;
    @ColumnInfo(name = "pasw")
    private String positiveComment;
    @ColumnInfo(name = "pimg")
    private String productImageUrl;
    @ColumnInfo(name = "pnm")
    private String productName;
    @ColumnInfo(name = "prc")
    private double price;
    @ColumnInfo(name = "rdt")
    private Date postedDate;
    @ColumnInfo(name = "simg")
    private String eshopImageUrl;
    @ColumnInfo(name = "snm")
    private String eshopName;
    @ColumnInfo(name = "surl")
    private String redirectUrl;


    public Product(String productId, String userImageUrl, String descriptionOfUser, String productBarcode, String negativeComment, String nameOfUser, String overallComments, int grade,
                   String positiveComment, String productImageUrl, String productName, double price, Date postedDate, String eshopImageUrl, String eshopName, String redirectUrl) {
        this.productId = productId;
        this.userImageUrl = userImageUrl;
        this.descriptionOfUser = descriptionOfUser;
        this.productBarcode = productBarcode;
        this.negativeComment = negativeComment;
        this.nameOfUser = nameOfUser;
        this.overallComments = overallComments;
        this.grade = grade;
        this.positiveComment = positiveComment;
        this.productImageUrl = productImageUrl;
        this.productName = productName;
        this.price = price;
        this.postedDate = postedDate;
        this.eshopImageUrl = eshopImageUrl;
        this.eshopName = eshopName;
        this.redirectUrl = redirectUrl;
    }

    @Ignore
    public Product(){

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getDescriptionOfUser() {
        return descriptionOfUser;
    }

    public void setDescriptionOfUser(String descriptionOfUser) {
        this.descriptionOfUser = descriptionOfUser;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public String getNegativeComment() {
        return negativeComment;
    }

    public void setNegativeComment(String negativeComment) {
        this.negativeComment = negativeComment;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }

    public String getOverallComments() {
        return overallComments;
    }

    public void setOverallComments(String overallComments) {
        this.overallComments = overallComments;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPositiveComment() {
        return positiveComment;
    }

    public void setPositiveComment(String positiveComment) {
        this.positiveComment = positiveComment;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getEshopImageUrl() {
        return eshopImageUrl;
    }

    public void setEshopImageUrl(String eshopImageUrl) {
        this.eshopImageUrl = eshopImageUrl;
    }

    public String getEshopName() {
        return eshopName;
    }

    public void setEshopName(String eshopName) {
        this.eshopName = eshopName;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Product fromJSON(JSONObject object) throws JSONException {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.productId = object.optString("PRID");
        this.userImageUrl = object.optString("CIMG");
        this.descriptionOfUser = object.optString("DTX");
        this.productBarcode = object.optString("EAN");
        this.negativeComment = object.optString("NASW");
        this.nameOfUser = object.optString("NCM");
        this.overallComments = object.optString("OCM");
        this.grade = object.optInt("ORT");
        this.positiveComment = object.optString("PASW");
        this.productImageUrl = object.optString("PIMG");
        this.productName = object.optString("PNM");
        this.price = object.optDouble("PRC");
        try {
            this.postedDate = formater.parse(object.optString("RDT"));
        } catch (ParseException e) {
            e.printStackTrace();
            this.postedDate = null;
        }
        this.eshopImageUrl = object.optString("SIMG");
        this.eshopName = object.optString("SNM");
        this.redirectUrl = object.optString("SURL");
        return this;
    }
}
