package com.faum.faum_expert;

/**
 * Created by arsal on 11-Nov-17.
 */

public class UserIdPhoneSet {
    private String id,phonenumber;

    public UserIdPhoneSet(){}

    public  UserIdPhoneSet(String phonenumber,String uid){
        this.phonenumber = phonenumber;
        this.id = uid;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public String getId() {
        return id;
    }
}
