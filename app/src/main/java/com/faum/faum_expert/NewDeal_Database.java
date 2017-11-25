package com.faum.faum_expert;

/**
 * Created by arsal on 02-Nov-17.
 */

public class NewDeal_Database {

    private String uId;

    private String  dealId,newDealCategory , dishName , dealDescription ;
    //private int dealName;
    private String dealName;



    private String EstimateTime ,NewDealPrice ,NewDealSize ;

    private Boolean eightToten ,twelveTotwo ,sixToeight, nineToeleven;


    private Boolean Monday,Tuesday,Wednesday,Thursday , Friday ;

    private Boolean checkBoxConfirmation;


    public NewDeal_Database(){

    }

    public NewDeal_Database(String uid,String dealId,String DealName,String NewDealCategory, String DishName, String DealDescription){
        this.uId = uid;
        this.dealId = dealId;
        this.dealName = DealName;
        this.newDealCategory = NewDealCategory;
        this.dishName = DishName;
        this.dealDescription = DealDescription;
    }

    public String getuId() {
        return uId;
    }

    public String getDealId() {
        return dealId;
    }

    public String getDealName() {
        return dealName;
    }

    public String getNewDealCategory() {
        return newDealCategory;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDealDescription() {
        return dealDescription;
    }

    public NewDeal_Database(String EstimateTime, String NewDealPrice, String NewDealSize){
        this.EstimateTime = EstimateTime;
        this.NewDealPrice = NewDealPrice;
        this.NewDealSize = NewDealSize;
    }

    public String getEstimateTime() {
        return EstimateTime;
    }

    public String getNewDealPrice() {
        return NewDealPrice;
    }

    public String getNewDealSize() {
        return NewDealSize;
    }

    public  NewDeal_Database(Boolean eightToten, Boolean twelveTotwo, Boolean sixToeight, Boolean nineToeleven){
        this.eightToten = eightToten;
        this.twelveTotwo = twelveTotwo;
        this.sixToeight  = sixToeight;
        this.nineToeleven = nineToeleven;
    }

    public Boolean getEightToten() {
        return eightToten;
    }

    public Boolean getTwelveTotwo() {
        return twelveTotwo;
    }

    public Boolean getSixToeight() {
        return sixToeight;
    }

    public Boolean getNineToeleven() {
        return nineToeleven;
    }

    public NewDeal_Database(Boolean Monday, Boolean Tuesday, Boolean Wednesday, Boolean Thursday, Boolean Friday){
        this.Monday = Monday;
        this.Tuesday = Tuesday;
        this.Wednesday = Wednesday;
        this.Thursday = Thursday;
        this.Friday = Friday;

    }

    public Boolean getMonday() {
        return Monday;
    }

    public Boolean getTuesday() {
        return Tuesday;
    }

    public Boolean getWednesday() {
        return Wednesday;
    }

    public Boolean getThursday() {
        return Thursday;
    }

    public Boolean getFriday() {
        return Friday;
    }


    public NewDeal_Database(Boolean checkBoxConfirmation,String dealId){
        this.dealId = dealId;
        this.checkBoxConfirmation = checkBoxConfirmation;
    }

    public Boolean getCheckBoxConfirmation() {
        return checkBoxConfirmation;
    }


}


