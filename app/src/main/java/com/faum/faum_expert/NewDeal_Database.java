package com.faum.faum_expert;

/**
 * Created by arsal on 02-Nov-17.
 */

public class NewDeal_Database {

    private String  NewDealCategory , DishName, DealDescription ;
    private int DealName;


    private String EstimateTime ,NewDealPrice ,NewDealSize ;

    private Boolean eightToten ,twelveTotwo ,sixToeight, nineToeleven;


    private Boolean Monday,Tuesday,Wednesday,Thursday , Friday ;


    public NewDeal_Database(){

    }

    public NewDeal_Database(int DealName,String NewDealCategory, String DishName, String DealDescription){
        this.DealName = DealName;
        this.NewDealCategory = NewDealCategory;
        this.DishName = DishName;
        this.DealDescription = DealDescription;
    }


    public int getDealName() {
        return DealName;
    }

    public String getNewDealCategory() {
        return NewDealCategory;
    }

    public String getDishName() {
        return DishName;
    }

    public String getDealDescription() {
        return DealDescription;
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
}
