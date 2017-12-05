package com.faum.faum_expert;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by arsal on 01-Dec-17.
 */

public class Confirm_Order_Database {

    String orderID,cookerID,userID,dealID,orderPrice,orderQty,dealName,dealCategory;


    public Confirm_Order_Database(){

    }
    public Confirm_Order_Database(String orderID,String cookerID,String userID,String dealID,String orderPrice,String orderQty,String dealName,String dealCategory){
        this.orderID = orderID;
        this.cookerID = cookerID;
        this.userID = userID;
        this.dealID = dealID;
        this.orderPrice = orderPrice;
        this.orderQty = orderQty;
        this.dealName = dealName;
        this.dealCategory = dealCategory;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getCookerID() {
        return cookerID;
    }

    public String getUserID() {
        return userID;
    }

    public String getDealID() {
        return dealID;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getOrderQty() {
        return orderQty;
    }

    public String getDealName() {
        return dealName;
    }

    public String getDealCategory() {
        return dealCategory;
    }
}
